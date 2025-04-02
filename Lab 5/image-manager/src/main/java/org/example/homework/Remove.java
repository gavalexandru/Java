package org.example.homework;
import org.example.compulsory.*;
public class Remove extends Command{
 public Remove(Repository repository, String[] args) {
     super(repository, args);
 }
 @Override
 public void execute() {
     try {
         if (args.length != 1) throw new InvalidArgumentsException("Remove command requires exactly 1 argument: image path");
         else {
             boolean ok = false;
             for (Image img : repository.getImages()) {
                 if (img.getPath().equals(args[0])) {
                     repository.remove(img);
                     ok = true;
                     System.out.println("Removed image " + img.getPath());
                     break;
                 }
             }
             if (!ok) {
                 throw new RepositoryOperationException("Image not found in repository: " + args[0]);
             }
         }
     }
     catch(ShellException e) {
         throw e;
     }
     catch(Exception e) {
         throw new RepositoryOperationException("Failed to remove image: " + e.getMessage());
     }
 }
}
