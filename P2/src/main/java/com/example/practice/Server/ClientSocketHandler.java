package com.example.practice.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = (ObjectOutputStream) clientSocket.getOutputStream();
        in = (ObjectInputStream) clientSocket.getInputStream();
    }

    public void write(Object obj) throws IOException
    {
        out.writeUnshared(obj);
    }

    public Object read() throws IOException, ClassNotFoundException {
        return in.readUnshared();
    }
}
