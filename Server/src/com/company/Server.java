package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static List<ClientHandler> clients;
    public static List<String> listname = new ArrayList<String>();
    ServerSocket serverSocket;
    //    static  int numOfUsers = 0;
    Socket socket;

    public Server() {
        clients = new ArrayList<ClientHandler>();
        try {
            serverSocket = new ServerSocket(Constants.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.watiConnection();
    }

    public static List<ClientHandler> getClients() {
        return clients;
    }

    private void watiConnection() {
        log("Server Running...");

        while (true) {
            try {
                socket = serverSocket.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }

            log("Client accepted: " + socket.getInetAddress());
//            numOfUsers++;
//            ClientHandler handler = new ClientHandler(socket, "user"+ numOfUsers);
            ClientHandler handler = new ClientHandler(socket);

            Thread thread = new Thread(handler);
            addClient(handler);
            thread.start();
        }
    }

    //    public static void setClients(List<Client> clients) {
//        Server.clients = clients;
//    }
    private void addClient(ClientHandler client) {
        clients.add(client);
    }

    private void log(String message) {
        System.out.println(message);
    }

    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }

}
