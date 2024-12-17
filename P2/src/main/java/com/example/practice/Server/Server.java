package com.example.practice.Server;

import com.example.practice.Database.DB;
import com.example.practice.Database.club;
import com.example.practice.Requests.AuthRequest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    protected static DB db;
    protected static ArrayList<ClientSocketHandler> clients = new ArrayList<>();

    void serve(Socket clientSocket) throws IOException
    {
        ClientSocketHandler client = new ClientSocketHandler(clientSocket);
        try {
            AuthRequest auth  = (AuthRequest) client.read();
            clientAccess(auth, client);
            if(auth.isAuthenticated())
            {
                clients.add(client);
                System.out.println("CLIENT AUTHENTICATED: "+client.getClubName());
                client.write(auth);
                new ServerListener(client);
            }
            else
            {
                client.write(auth);
                client.close();
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    void clientAccess(AuthRequest auth, ClientSocketHandler client) {
        club clubObject = db.findClub(auth.getClubName());

        if(clubObject == null) {
            auth.setAuthenticated(false);
            auth.setError("Club not present in database");
        }
        else
        {
            if(clubObject.getPassword().equals(auth.getPassword()))
            {
                auth.setAuthenticated(true);
                client.setClubName(clubObject.getName());
                auth.setClubName(clubObject.getName());
            }
            else
            {
                auth.setAuthenticated(false);
                auth.setError("Wrong password");
            }
        }
    }

    private static void shutdown() {
        System.out.println("Server Shutdown:");
        try {
            db.writeToFile("src/main/java/com/example/practice/Database/players.txt");
            System.out.println("\tData Export Successful");
            for(ClientSocketHandler client : clients)
            {
                client.close();
            }
            System.out.println("\tClients Disconnected");
            System.out.println("Server Shutdown Complete");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Server() throws Exception {

        Runtime.getRuntime().addShutdownHook(new Thread(Server::shutdown));

        ServerSocket serverSocket = new ServerSocket(11111);
        System.out.println("Server started");
        while (true) {
            try {
                Socket client = serverSocket.accept();
                serve(client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        db = new DB();
        db.input();
        new Server();
    }

}
