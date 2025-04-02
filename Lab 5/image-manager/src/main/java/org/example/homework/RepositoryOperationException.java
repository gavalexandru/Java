package org.example.homework;

public class RepositoryOperationException extends ShellException {
    public RepositoryOperationException(String message) {
        super("Repository operation failed: " + message);
    }
}
