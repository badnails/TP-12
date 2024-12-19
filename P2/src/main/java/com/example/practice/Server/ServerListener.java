package com.example.practice.Server;

import com.example.practice.Database.player;
import com.example.practice.Requests.EndSession;
import com.example.practice.Requests.RefreshNotif;
import com.example.practice.Requests.TransferTicket;
import com.example.practice.Requests.searchQuery;

import java.io.IOException;

public class ServerListener implements Runnable {

    private final ClientSocketHandler client;
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
                System.out.println("Packet received from: " + client.getClubObject().getName());
                if(received instanceof searchQuery)
                {
                    System.out.println("--------"+"Search Query from: " + client.getClubObject().getName()+"--------");
                    searchQuery send = Server.db.searchFunction((searchQuery) received, client.getClubObject().getName());
                    for(player temp: send.getResults())
                    {
                        System.out.println(temp.getName()+" "+temp.isTransferListed());
                    }
                    client.reset();
                    send.setFrom("Server"+count++);
                    client.write(send);
                }
                if(received instanceof TransferTicket)
                {
                    System.out.println("--------"+"Transfer Ticket from: " + client.getClubObject().getName()+"--------");
                    TransferTicket response = Server.db.transferRequest((TransferTicket) received, client.getClubObject().getName());
                    client.reset();
                    client.write(response);
                    for(ClientSocketHandler Allclient: Server.clients)
                    {
                        Allclient.write(new RefreshNotif());
                    }
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
