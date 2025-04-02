package org.example.homework;

public class FileOperationException extends ShellException {
    public FileOperationException(String message) {
        super("File operation failed: " + message);
    }
}