package controller;

import data.TrackDataObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SaveLoadService {
    private static SaveLoadService instance;

    private SaveLoadService(){
    }

    public static SaveLoadService getInstance(){
        if(instance == null){
            instance = new SaveLoadService();
        }
        return instance;
    }

    public void save(ObjectOutputStream objectOutputStream, Serializable obj) throws IOException {
        objectOutputStream.writeObject(obj);
    }

    public List<TrackDataObject> load(ObjectInputStream in) throws IOException, ClassNotFoundException {
        return (List<TrackDataObject>) in.readObject();
    }
}
