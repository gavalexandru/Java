package org.example.homework;

public class InvalidCommandException extends ShellException {
    public InvalidCommandException(String command) {
        super("Invalid command: " + command);
    }
}