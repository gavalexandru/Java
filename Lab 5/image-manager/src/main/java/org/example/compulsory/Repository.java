package org.example.compulsory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.homework.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Repository {
    private List<Image> images = new ArrayList<>();
    public void add(Image image) {
        images.add(image);
    }
    public void remove(Image image) {
        images.remove(image);
    }
    public List<Image> getImages() {
        return images;
    }
    public void loadFromTextFile(String path) {
            images.clear();
            try(Scanner text = new Scanner(new File(path))){
            while (text.hasNextLine()) {
                String line = text.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] args = line.split("\\s+");
                if(args.length < 4){
                    throw new InvalidArgumentsException("Each line must contain at least 4 arguments");
                }
                else{
                    String name = args[0];
                    LocalDateTime time;
                    try {
                        time = LocalDateTime.parse(args[1]);
                    }
                    catch (DateTimeParseException e) {
                        throw new InvalidArgumentsException("Invalid timestamp format at line:" + line + "\n" + " Expected format: yyyy-MM-ddTHH:mm:ss");
                    }
                    List<String> tags = new ArrayList<>();
                    for (int i = 2; i < args.length - 1; i++) {
                        if (args[i].isBlank()) throw new InvalidArgumentsException("Empty tag at line: " + line);
                        tags.add(args[i]);
                    }
                    boolean ok = false;
                    for (Image img : images) {
                        if (img.getPath().equals(args[args.length-1])) ok = true;
                    }
                    Image img = new Image(name,time,tags,args[args.length-1]);
                    if (!ok) {
                        add(img);
                        System.out.println("Image loaded from txt: " + img.getPath());
                    }
                    else throw new RepositoryOperationException("Duplicate image path found: " + args[args.length-1] + "\n" + "Original line: " + line);
                }
            }
        }
        catch(ShellException e){
            throw e;
        }
        catch(Exception e){
            throw new RepositoryOperationException("Failed to load image - txt format: " + e.getMessage());
        }
    }
    public void loadFromJsonFile(String path) {
        images.clear();
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Image> loadedImages = mapper.readValue(new File(path), new TypeReference<List<Image>>() {});
            for(Image img : loadedImages){
                if (img == null || img.getPath() == null || img.getPath().isBlank()) throw new InvalidArgumentsException("Invalid image entry in JSON - missing path");
            }
            for(Image img : loadedImages){
                boolean ok = false;
                for(Image img2 : images){
                    if(img2.getPath().equals(img.getPath())){
                        ok = true;
                        break;
                    }
                }
                if(!ok){
                    add(img);
                    System.out.println("Image loaded from JSON: " + img.getPath());
                }
                else{
                    throw new RepositoryOperationException("Image already exists in repository with path: " + img.getPath());
                }
            }
        }
        catch (JsonProcessingException e) {
            throw new InvalidArgumentsException("Invalid JSON format: " + e.getOriginalMessage());
        }
        catch (IOException e) {
            throw new RepositoryOperationException("Failed to read JSON file: " + e.getMessage());
        }
        catch(ShellException e){
          throw e;
        }
        catch(Exception e){
            throw new RepositoryOperationException("Failed to load image - JSON format: " + e.getMessage());
        }
    }
    public void loadFromBinaryFile(String path) {
        images.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))) {
            List<Image> loadedImages = (List<Image>) ois.readObject();
            for (Image img : loadedImages) {
                if (img == null || img.path() == null || img.path().isBlank())
                    throw new InvalidArgumentsException("Invalid image entry in binary file - missing path");
            }
            for (Image img : loadedImages) {
                boolean ok = false;
                for (Image img2 : images) {
                    if (img2.getPath().equals(img.getPath())) {
                        ok = true;
                        break;
                    }
                }
                if (!ok) {
                    add(img);
                    System.out.println("Image loaded from Binary: " + img.getPath());
                } else {
                    throw new RepositoryOperationException("Image already exists in repository with path: " + img.getPath());
                }
            }
        }
        catch (ClassNotFoundException e) {
            throw new RepositoryOperationException(
                    "Invalid binary format: Class not found - " + e.getMessage());
        }
        catch (InvalidClassException e) {
            throw new RepositoryOperationException(
                    "Version mismatch in binary file: " + e.getMessage());
        }
        catch (StreamCorruptedException e) {
            throw new RepositoryOperationException(
                    "Corrupted binary file: " + e.getMessage());
        }
        catch (IOException e) {
            throw new RepositoryOperationException(
                    "Failed to read binary file: " + e.getMessage());
        }
        catch(ShellException e){
            throw e;
        }
        catch (Exception e) {
            throw new RepositoryOperationException(
                    "Unexpected error loading binary file: " + e.getMessage());
        }
    }
    public void saveToTextFile(String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for(Image img : images){
                writer.println(img.getName() + " " + img.getDateTime().toString() + " " + img.getTags() + " " + img.getPath());
                System.out.println("Saved image " + img.getPath());
            }
        }
        catch(ShellException e){
            throw e;
        }
        catch(Exception e){
            throw new RepositoryOperationException("Failed to save image data - txt format: " + e.getMessage());
        }
    }
    public void saveToJsonFile(String path)  {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(path), images);
            System.out.println("Images have been saved to JSON: " + path);
        }
        catch(ShellException e){
            throw e;
        }
        catch(Exception e){
            throw new RepositoryOperationException("Failed to save image data - JSON format: " + e.getMessage());
        }
    }
    public void saveToBinaryFile(String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(path)))) {
            oos.writeObject(images);
        }
        catch(ShellException e){
            throw e;
        }
        catch(Exception e){
            throw new RepositoryOperationException("Failed to save image data - binary format: " + e.getMessage());
        }
    }
}

