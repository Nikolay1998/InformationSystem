package net;

import controller.DataUpdateListener;
import data.TrackDataObject;
import model.Event;
import model.FullModel;
import model.Observable;
import view.EventListener;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class SocketW implements DataUpdateObservable {
    private final int port;
    private List<DataUpdateListener> listeners = new LinkedList<>();
    private String address;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public SocketW(String address, int port) {
        this.port = port;
        this.address = address;
    }

    public void addTrack(TrackDataObject trackDataObject) {
        ServerMessage message = new ServerMessage(ServerCommands.ADD_TRACK, trackDataObject);
        sendMessage(message);
    }

    @Override
    public void subscribe(DataUpdateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void unsubscribe(DataUpdateListener listener) {
        listeners.remove(listener);
    }

    public void connect() {
        try {
            while (true) {
                try {
                    socket = new Socket(address, port);
                    break;
                } catch (IOException ignored) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Loss connection with server");
                    } catch (InterruptedException ignored2) {
                    }
                }
            }

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connection to Server successful!");
            new Thread(receiving).start();
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }
    }



    public void sendMessage(ServerMessage msg){
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't send message to Server!");
        }
    }


    private Runnable receiving = () -> {
        while (true) {
            try {
                LinkedList message = (LinkedList<TrackDataObject>) in.readObject();
               // switch (message.getCommand()) {
                 //   case ServerCommands.CONNECT: {
                System.out.println("Receive data!");
                System.out.println(message.size());
                        for(DataUpdateListener listener : listeners){
                            //listener.update((FullModel) message.getData());


                            //System.out.println(((FullModel) message.getData()).getTackListArr().size());
                   //     }
                   // }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };


    private void disconnect() {
        try {
            socket.close();
        } catch (IOException ignored) {
        }
        try {
            in.close();
        } catch (IOException ignored) {
        }
        try {
            out.close();
        } catch (IOException ignored) {
        }
    }


}
