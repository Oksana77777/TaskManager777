package org.example;

import com.google.gson.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();//ждем подключения
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    Gson gson = new GsonBuilder().create();
                    JsonParser parser = new JsonParser();
                    Object obj = parser.parse(in.readLine());
                    JsonObject jsonObject = (JsonObject) obj;
                    operation(jsonObject);
                    String deleteString = todos.getAllTasks();
                    out.println(deleteString);
                }
            }
        }
    }

    private void operation(JsonObject obj) {
        String typeOperation = obj.get("type").getAsString();
        switch (typeOperation) {
            case "ADD":
                todos.addTask(obj.get("task").getAsString());
                break;
            case "REMOVE":
                todos.removeTask(obj.get("task").getAsString());
                break;
            case "RESTORE":
                todos.restore();
                break;
            default:
                System.out.println("Неизвестная операция - " + typeOperation);
                throw new RuntimeException();
        }
    }
}
