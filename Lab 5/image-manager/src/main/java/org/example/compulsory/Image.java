package org.example.compulsory;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.homework.StringToListDeserializer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;
import java.util.Objects;

public record Image(String name, LocalDateTime dateTime, @JsonDeserialize(using = StringToListDeserializer.class) List<String> tags, String path) implements Serializable {
    private static final long serialVersionUID = 1L;
    public Image {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(dateTime, "DateTime cannot be null");
        Objects.requireNonNull(path, "Path cannot be null");
        tags = List.copyOf(tags); //copy
    }
    public void display() {
       File file = new File(path);
       try {
           Desktop desktop = Desktop.getDesktop();
           if (!Desktop.isDesktopSupported()) {
               throw new UnsupportedOperationException("Desktop operations are not supported on this platform.");
           }
           if (!file.exists()) {
               throw new IOException("File not found: " + name + " " + path);
           }
           desktop.open(file);
       }
       catch (UnsupportedOperationException e) {
           System.err.println("System compatibility error:" + e.getMessage());
       }
       catch (IOException e) {
           System.err.println("File access error: " + e.getMessage());
       }
       catch (Exception e) {
           System.err.println("Unexpected error while displaying image: " + e.getMessage());
       }
    }
    public String getPath(){
        return path;
    }
    public void update(List<String> tags){
        this.tags.clear();
        this.tags.addAll(tags);
    }
    public String getName(){
        return name;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public String getTags(){
        String s ="";
        for(String tag : tags){
            s = s + tag + " ";
        }
        return s;
    }
}
