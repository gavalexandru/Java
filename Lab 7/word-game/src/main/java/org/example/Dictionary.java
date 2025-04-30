package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
public class Dictionary {
private final Set<String> words = new HashSet<>();
public Dictionary(String path) {
    try{
        Files.lines(Paths.get(path))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(word -> word.matches("[a-z]+"))
                .forEach(words::add);
    }
    catch(IOException e){
        throw new RuntimeException("Failed to load dictionary" + e.getMessage());
    }
 }
 public boolean isValidWord(String word) {
    return words.contains(word.toLowerCase());
 }
 public int size(){
    return words.size();
 }
}
