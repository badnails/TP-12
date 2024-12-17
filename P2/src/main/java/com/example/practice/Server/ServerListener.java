package com.example.practice.Server;

import com.example.practice.Database.player;
import com.example.practice.Requests.EndSession;
import com.example.practice.Requests.TransferTicket;
import com.example.practice.Requests.searchQuery;

import java.io.IOException;

public class ServerListener implements Runnable {

    private ClientSocketHandler client;
    ServerListener(ClientSocketHandler client)
    {
        this.client = client;
        Thread t = new Thread(this);
        t.start();
    }
    int count = 0;

    @Override
    public void run() {
        try {
            while(true)
            {
                Object received = client.read();
                System.out.println("Packet received from: " + client.getClubName());
                if(received instanceof searchQuery)
                {
                    System.out.println("--------"+"Search Query from: " + client.getClubName()+"--------");
                    searchQuery send = Server.db.searchFunction((searchQuery) received, client.getClubName());

                    client.reset();
                    client.write(send);
                }
                if(received instanceof TransferTicket)
                {
                    System.out.println("--------"+"Transfer Ticket from: " + client.getClubName()+"--------");
                    TransferTicket response = Server.db.transferRequest((TransferTicket) received, client.getClubName());
                    client.write(response);
                }
                if(received instanceof EndSession)
                {
                    break;
                }

            }
        } catch (IOException | ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                client.close();
                Server.clients.remove(client);
                System.out.println("Server Listener Thread Terminated");
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}
