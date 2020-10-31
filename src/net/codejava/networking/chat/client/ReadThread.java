package net.codejava.networking.chat.client;

import java.io.*;
import java.net.*;

public class ReadThread extends Thread {
    private BufferedReader reader;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient client) {
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException error) {
            System.out.println("Error bei Eingabe: " + error.getMessage());
            error.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);

                if (client.getUserName() != null) {
                    System.out.print("|" + client.getUserName() + "|: ");
                }
            } catch (IOException error) {
                System.out.println("Error bei der Nachricht: " + error.getMessage());
                error.printStackTrace();
                break;
            }
        }
    }
}