package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;
    private PrintWriter out;
    private BufferedReader in;

    public ClientThread(Socket socket, GameServer server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Received from client: " + line);

                if ("stop".equalsIgnoreCase(line)) {
                    out.println("Server stopping...");
                    server.stop();
                    break;
                }
                else {
                    out.println("Server received the request: " + line);
                }
            }

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Client thread error: " + e.getMessage());
        }
    }
}