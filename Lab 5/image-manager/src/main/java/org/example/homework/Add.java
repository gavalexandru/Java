package org.example.homework;
import org.example.compulsory.Repository;
import org.example.compulsory.*;
import org.w3c.dom.stylesheets.StyleSheetList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Add extends Command{
public Add(Repository repository, String[] args) {
   super(repository, args);
 }
 @Override
 public void execute() {
     try {
         if (args.length < 4) throw new InvalidArgumentsException("Add command requires at least 4 arguments");
         else {
             String name = args[0];
             LocalDateTime time;
             try {
                 time = LocalDateTime.parse(args[1]);
             } catch (DateTimeParseException e) {
                 throw new InvalidArgumentsException("Invalid timestamp format. Expected format: yyyy-MM-ddTHH:mm:ss");
             }
             List<String> tags = new ArrayList<String>();
             for (int i = 2; i < args.length - 1; i++) {
                 if (args[i].isBlank()) throw new InvalidArgumentsException("Tags cannot be empty");
                 tags.add(args[i]);
             }
             String path = args[args.length - 1];
             Image image = new Image(name, time, tags, path);
             boolean ok = false;
             for (Image img : repository.getImages()) {
                 if (img.getPath().equals(path)) ok = true;
             }
             if (!ok) {
                 repository.add(image);
                 System.out.println("Added image: " + image.getPath());
             }
             else throw new RepositoryOperationException("Image already exists in repository with path: " + path);
         }
     }
     catch(ShellException e) {
        throw e;
     }
     catch (Exception e) {
         throw new RepositoryOperationException("Failed to add image: " + e.getMessage());
     }
 }
}
