package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;

    public void startConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        scanner = new Scanner(System.in);

        System.out.println("Connected to server at " + ip + ":" + port);

        String userInput;
        while (true) {
            System.out.print("Enter command (or 'exit' to quit): ");
            userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                break;
            }

            out.println(userInput);
            String response = in.readLine();
            System.out.println("Server response: " + response);
        }

        stopConnection();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        socket.close();
        scanner.close();
        System.out.println("Connection closed");
    }

    public static void main(String[] args) {
        GameClient client = new GameClient();
        try {
            client.startConnection("localhost", 12345);
        }
        catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}