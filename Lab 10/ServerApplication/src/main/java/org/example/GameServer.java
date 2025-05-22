package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;
    private boolean running;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        System.out.println("Server started on port " + port);
    }

    public void start() {
        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            }
            catch (IOException e) {
                if (running) {
                    System.out.println("Server error: " + e.getMessage());
                }
            }
        }
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
            System.out.println("Server stopped");
        }
        catch (IOException e) {
            System.out.println("Error closing server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            GameServer server = new GameServer(12345);
            server.start();
        }
        catch (IOException e) {
            System.out.println("Could not start server: " + e.getMessage());
        }
    }
}