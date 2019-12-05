package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
}
