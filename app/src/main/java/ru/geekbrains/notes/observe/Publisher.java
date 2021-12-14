package ru.geekbrains.notes.observe;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notes.data.Notes;

public class Publisher {
    private List<Observer> observers;

    public Publisher(){
        observers = new ArrayList<>();
    }

    public void subscribe(Observer observer){
        observers.add(observer);
    }

    public void unsubscribe(Observer observer){
        observers.remove(observer);
    }

    public void notifySingle(Notes notes){
        for (Observer observer : observers){
            observer.updateCardData(notes);
            unsubscribe(observer);
        }
    }
}
