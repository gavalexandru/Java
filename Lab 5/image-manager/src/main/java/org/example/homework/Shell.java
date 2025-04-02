package org.example.homework;
import org.example.compulsory.*;

import java.util.Arrays;
import java.util.Scanner;

public class Shell {
private Scanner scanner;
private Repository repository = new Repository();
boolean running;
public Shell() {
    scanner = new Scanner(System.in);
    start();
}
public void start(){
    System.out.println("Image Manager");
    running = true;
    while(running){
        try {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            processComand(input);
        }
        catch (ShellException e) {
            System.err.println("Shell Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
    scanner.close();
    }
    private void processComand(String input) throws ShellException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException("Empty input");
        }
        String[] parts = input.split("\\s+");
        String commandName = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        Command command = createCommand(commandName,args);
        command.execute();
    }
    private Command createCommand(String commandName, String[] args) throws InvalidCommandException {
        return switch (commandName.toLowerCase()) {
            case "add" -> new Add(repository, args);
            case "remove" -> new Remove(repository, args);
            case "update" -> new Update(repository, args);
            case "load" -> new Load(repository, args);
            case "save" -> new Save(repository, args);
            case "report" -> new Report(repository, args);
            case "help" -> new Help(repository, args);
            case "exit" -> {
                running = false;
                yield new Exit(repository, args);
            }
            default ->  throw new InvalidCommandException(commandName);
        };
    }
}
