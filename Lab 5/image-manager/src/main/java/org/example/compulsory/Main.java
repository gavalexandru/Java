package org.example.compulsory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String s = "C:\\Users\\Mesul\\Pictures\\Screenshots\\picture.png";
        List<String> l = new ArrayList<String>();
        l.add("a");
        l.add("b");
        l.add("c");
        String name = "picture";
        Repository repo = new Repository();
        Image image = new Image(name,LocalDateTime.now(),l,s);
        repo.add(image);
        image.display();
    }
}