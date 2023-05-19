package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final int PORT = 8989;
        final String HOST = "localhost";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try (Socket socket = new Socket(HOST, PORT)) {
                try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        System.out.println("Укажите тип операции:");
                        String typeOperation = scanner.nextLine();
                        System.out.println("Укажите название задачи:");
                        String nameTask = scanner.nextLine();
                        String requestString = "{ \"type\": \"" + typeOperation + "\", \"task\": \"" + nameTask + "\" }";
                        out.println(requestString);
                        System.out.println(in.readLine());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}