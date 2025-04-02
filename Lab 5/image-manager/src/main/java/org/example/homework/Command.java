package org.example.homework;
import org.example.compulsory.Repository;

public class Command {
    protected Repository repository;
    protected String[] args;
    public Command(Repository repository, String[] args) {
        this.repository = repository;
        this.args = args;
    }
 public void execute(){

 }
}
