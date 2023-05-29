package com.ufes.dadosclimaticos.model.observer;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import java.util.ArrayList;
import java.util.List;


public class DadosClimaticosObservable implements IObservable {
    private final List<IObserver> observers;
    private final List<DadosClimaticos> dadosClimaticos;

    public DadosClimaticosObservable(List<DadosClimaticos> dadosClimaticos) {
        this.observers = new ArrayList<>();
        this.dadosClimaticos = dadosClimaticos;
    }
    
   public void addDados(DadosClimaticos dados) {
        dadosClimaticos.add(dados);
        notifyObservers();
    }

    public void removeDados(DadosClimaticos dados) {
        dadosClimaticos.remove(dados);
        notifyObservers();
    }

    public List<DadosClimaticos> getDadosList() {
        return dadosClimaticos;
    }
    
    @Override
    public void addObserver(IObserver... observers) {
        for(IObserver observer : observers) {
            if(!this.observers.contains(observer)){
                this.observers.add(observer);
            }
        }
    }

    @Override
    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers) {
            observer.update(this);
        }
    }
}
