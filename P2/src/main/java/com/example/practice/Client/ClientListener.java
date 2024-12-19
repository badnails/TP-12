package com.example.practice.Client;

import com.example.practice.Requests.EndSession;
import com.example.practice.Requests.RefreshNotif;
import com.example.practice.Server.ClientSocketHandler;
import javafx.application.Platform;

public class ClientListener implements Runnable {
    private Thread t;
    private ClientSocketHandler clientSocketHandler;
    ClientListener(ClientSocketHandler clientSocketHandler)
    {
        this.clientSocketHandler = clientSocketHandler;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                Object received = clientSocketHandler.read();
                if(received instanceof RefreshNotif)
                {
                    Platform.runLater(()-> Client.currentController.refresh());
                }
                if(received instanceof EndSession)
                {
                    Platform.runLater(Platform::exit);
                    break;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
