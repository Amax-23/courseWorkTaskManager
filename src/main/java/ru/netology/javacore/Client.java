package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;


public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "127.0.0.1";
    private static IncomingTask task = new IncomingTask();


    public static void main(String[] args) {
        task.setType("RESTORE");
        task.setTask("Третья");
        Gson gson = new Gson();
        String params = gson.toJson(task);
        try (Socket clientSocet = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocet.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocet.getInputStream()))) {
            //System.out.println(params);
            out.println(params);
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
