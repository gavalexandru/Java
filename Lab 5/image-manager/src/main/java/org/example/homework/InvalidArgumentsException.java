package org.example.homework;

public class InvalidArgumentsException extends ShellException {
  public InvalidArgumentsException(String message) {
    super("Invalid arguments: " + message);
  }
}