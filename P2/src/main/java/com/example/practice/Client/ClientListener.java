package com.example.practice.Client;

import com.example.practice.Requests.EndSession;
import com.example.practice.Requests.RefreshNotif;
import com.example.practice.Requests.TransferTicket;
import com.example.practice.Requests.searchQuery;
import com.example.practice.Server.ClientSocketHandler;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;

public class ClientListener implements Runnable {
    private Thread t;
    ClientListener()
    {
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
                Object received = new Object();
                if(Client.connected)
                {
                    received = Client.socket.read();
                }

                if(received instanceof searchQuery)
                {
                    searchQuery response = (searchQuery) received;
                    Platform.runLater(()->{
                        if(Client.controller instanceof PlayerSearchMenuController)
                        {
                            PlayerSearchMenuController cont = (PlayerSearchMenuController)Client.controller;
                            try {
                                cont.listFiller(response.getResults());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(Client.controller instanceof TransferMenuController)
                        {
                            TransferMenuController cont = (TransferMenuController)Client.controller;
                            try {
                                cont.listFiller(response.getResults());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if(received instanceof TransferTicket)
                {
                    TransferTicket response = (TransferTicket) received;
                    Platform.runLater(()->{
                        if(Client.controller instanceof PlayerSearchMenuController)
                        {
                            if(response.getSuccess())
                            {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText(null);
                                alert.setContentText(response.getPlayerObject().getName()+" has been added to transfer market");
                                alert.showAndWait();
                            }
                            else
                            {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText(null);
                                alert.setContentText(response.getPlayerObject().getName()+" could not be added to transfer market");
                                alert.showAndWait();
                            }
                        }
                        if(Client.controller instanceof TransferMenuController)
                        {
                            if(response.getSuccess())
                            {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText(null);
                                alert.setContentText(response.getPlayerObject().getName()+" has been purchased!");
                                alert.showAndWait();
                            }
                            else
                            {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText(null);
                                alert.setContentText(response.getPlayerObject().getName()+" could not be purchased!");
                                alert.showAndWait();
                            }
                            if(Client.controller instanceof TransferMenuController)
                            {
                                ((TransferMenuController)Client.controller).requestSearch(0);
                            }
                        }
                    });
                }
                if(received instanceof RefreshNotif)
                {
                    Platform.runLater(()->{
                        if(Client.controller instanceof PlayerSearchMenuController)
                        {
                            ((PlayerSearchMenuController)Client.controller).requestSearch(0);
                        }
                        if(Client.controller instanceof TransferMenuController)
                        {
                            ((TransferMenuController)Client.controller).requestSearch(0);
                        }
                    });
                }
                if(received instanceof EndSession)
                {
                    if(!((EndSession) received).isLogout())
                    {
                        Platform.runLater(Platform::exit);
                    }
                    if(Client.socket!=null)
                        Client.socket.close();
                    Client.connected = false;
                    Client.socket = null;
                    Client.controller = null;
                    Client.clubObject = null;
                    Client.lastSearchQ = new searchQuery(1);
                    Client.lastTransferQ = new searchQuery(2);
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
