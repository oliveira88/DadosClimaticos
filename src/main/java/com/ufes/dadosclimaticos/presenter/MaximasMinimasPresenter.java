package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.IObserver;
import com.ufes.dadosclimaticos.view.MaximasMinimas;
import java.util.List;


public class MaximasMinimasPresenter  implements IObserver {

    private final MaximasMinimas view;
    private List<DadosClimaticos> listDadosClimaticos;
    
    public MaximasMinimasPresenter(List<DadosClimaticos> listDadosClimaticos) {
        this.view = new MaximasMinimas();
        this.listDadosClimaticos = listDadosClimaticos;
        listDadosClimaticos.forEach(dado -> {
        
        });
        this.view.setMaxima(maxima);
        this.view.setVisible(true);
    }
    
     public MaximasMinimas getView() {
        return view;
    }

    @Override
    public void update(DadosClimaticos dadosCimaticos) {
        this.listDadosClimaticos.add(dadosCimaticos);
    }
}

