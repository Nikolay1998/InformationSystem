package net;

import controller.DataUpdateListener;
import view.EventListener;

public interface DataUpdateObservable {
    void subscribe(DataUpdateListener listener);
    void unsubscribe(DataUpdateListener listener);
}
