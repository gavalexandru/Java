package org.example.homework;
import org.example.compulsory.*;

public class Load extends Command{
    public Load(Repository repository, String[] args) {
        super(repository, args);
    }
    @Override
    public void execute() {
       if(args.length != 1) throw new InvalidArgumentsException("Load command requires 1 argument");
       else {
           try {
               String path = args[0];
               if (path.toLowerCase().endsWith("txt")) {
                   repository.loadFromTextFile(path);
               } else if (path.toLowerCase().endsWith("json")) {
                   repository.loadFromJsonFile(path);
               } else if (path.toLowerCase().endsWith(".dat") || path.toLowerCase().endsWith(".ser")) {
                   repository.loadFromBinaryFile(path);
               } else
                   throw new UnsupportedOperationException("Unsupported file format: " + path + "\n" + "Supported formats: .txt, .json, .dat/.ser");
           }
           catch(ShellException e){
               throw e;
           }
           catch(Exception e){
               throw new RepositoryOperationException("Failed to load images: " + e.getMessage());
           }
       }
       }
    }
