package com.ufes.dadosclimaticos.service;

import com.ufes.dadosclimaticos.logger.ILogger;
import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.IObserver;
import java.util.ArrayList;
import java.util.List;

public class EstacaoClimaticaService {
    private final ILogger log;
    private List<IObserver> observers;

    public EstacaoClimaticaService(ILogger log){
        this.log = log;
        this.observers = new ArrayList<>();
    }    
    
     public void addObserver(IObserver observer){
         this.observers.add(observer);
     }
    
    public void removeObserver(IObserver observer){
        this.observers.remove(observer);
    }
    
    public void salvarDadosClimaticos(DadosClimaticos dadoClimatico) throws Exception{
        this.log.logSalvar(dadoClimatico);
        this.notifyObservers(dadoClimatico);
    }
    
    
    
    public void notifyObservers(DadosClimaticos dadoClimatico){
      List<IObserver> list = observers;
        for(IObserver observer : list){
            observer.update(dadoClimatico);
        }
    }

}
