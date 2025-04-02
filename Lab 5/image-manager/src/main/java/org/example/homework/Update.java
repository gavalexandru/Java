package org.example.homework;
import org.example.compulsory.*;

import java.util.ArrayList;
import java.util.List;

public class Update extends Command{
    public Update(Repository repository, String[] args) {
        super(repository, args);
    }
    @Override
    public void execute(){
        try {
            if (args.length < 2) throw new InvalidArgumentsException("Update command requires at least 2 arguments: image path and at least one tag");
            else {
                String path = args[0];
                List<String> tags = new ArrayList<String>();
                boolean ok = false;
                for (int i = 1; i < args.length; i++) {
                    if (args[i].trim().isEmpty()) throw new InvalidArgumentsException("Tags cannot be empty");
                    tags.add(args[i]);
                }
                for (Image img : repository.getImages()) {
                    if (img.getPath().equals(path)) {
                        ok = true;
                        img.update(tags);
                        System.out.println("Image tags have been updated successfully: " + path);
                        break;
                    }
                }
                if (!ok) {
                    throw new RepositoryOperationException("Image not found in repository: " + path);
                }
            }
        }
        catch (ShellException e){
            throw e;
        }
        catch (Exception e){
            throw new RepositoryOperationException("Failed to update image tags: " + e.getMessage());
        }
    }
}
