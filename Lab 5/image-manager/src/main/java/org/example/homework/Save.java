package org.example.homework;
import org.example.compulsory.Repository;

public class Save extends Command{
    public Save(Repository repository, String[] args) {
        super(repository, args);
    }
    @Override
    public void execute(){
        if(args.length != 1) throw new InvalidArgumentsException("Save command requires 1 argument");
        else {
            try {
                String path = args[0];
                if (path.toLowerCase().endsWith("txt")) {
                    repository.saveToTextFile(path);
                } else if (path.toLowerCase().endsWith("json")) {
                    repository.saveToJsonFile(path);
                } else if (path.toLowerCase().endsWith(".dat") || path.toLowerCase().endsWith(".ser")) {
                    repository.saveToBinaryFile(path);
                } else
                    throw new UnsupportedOperationException("Unsupported file format: " + path + "\n" + "Supported formats: .txt, .json, .dat/.ser");
            }
            catch(ShellException e){
                throw e;
            }
            catch(Exception e){
                throw new RepositoryOperationException("Failed to save images: " + e.getMessage());
            }
        }
    }
}
