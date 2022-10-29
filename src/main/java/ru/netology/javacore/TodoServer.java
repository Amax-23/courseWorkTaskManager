package ru.netology.javacore;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class TodoServer {
    protected int port;
    protected Todos todos;
    protected static ArrayDeque<List> log = new ArrayDeque<>();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started!");
            while (true) {
                try (Socket clientSocet = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocet.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocet
                             .getInputStream()))) {
                    System.out.println("New connection accepted!");
                    todos.readIncomingMsg(in.readLine());
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер!");
            e.printStackTrace();
        }
    }

    public static ArrayDeque<List> getLog() {
        return log;
    }
}
