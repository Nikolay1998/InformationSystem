package net;

import controller.DataUpdateListener;
import data.TrackDataObject;
import model.FullModel;

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

    public void removeTrack(String id) {
        ServerMessage message = new ServerMessage(ServerCommands.DELETE_TRACK, id);
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
                        ignored2.printStackTrace();
                    }
                }
            }
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connection to Server successful!");
            new Thread(receiver).start();
            sendMessage(new ServerMessage(ServerCommands.UPDATE_DATA, null));
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


    private Runnable receiver = () -> {
        while (true) {
            try {
                ServerMessage message = (ServerMessage) in.readObject();
                switch (message.getCommand()) {
                    case ServerCommands.UPDATE_DATA: {
                        System.out.println("Received data: ");
                        FullModel newModel = (FullModel) message.getData();
                        for(DataUpdateListener listener : listeners){
                            System.out.println(newModel.getTackListArr().toString() + "\nStored data:");
                            listener.update(newModel);
                        }
                   }
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
