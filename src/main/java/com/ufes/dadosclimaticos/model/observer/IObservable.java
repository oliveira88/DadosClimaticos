package com.ufes.dadosclimaticos.model.observer;

public interface IObservable {
    public void addObserver(IObserver... observer);
    public void removeObserver(IObserver observer);
    public void notifyObservers();
}
