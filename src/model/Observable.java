package model;

import view.EventListener;

public interface Observable {
    void subscribe(EventListener eventListener);
    void unsubscribe(EventListener eventListener);
}
