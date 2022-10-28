package ru.netology.javacore;

import java.io.*;
import java.net.Socket;



public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        try (Socket clientSocet = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocet.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocet.getInputStream()))) {
            out.println("testJson.json");
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
