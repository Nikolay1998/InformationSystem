package controller;

import data.TrackDataObject;

import java.io.*;
import java.util.List;

public class SaveLoadService {
    private static SaveLoadService instance;

    private SaveLoadService() {
    }

    public static SaveLoadService getInstance() {
        if (instance == null) {
            instance = new SaveLoadService();
        }
        return instance;
    }

    public void save(File file, List<TrackDataObject> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(obj);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }

    public List<TrackDataObject> load(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<TrackDataObject>) in.readObject();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
