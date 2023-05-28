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
        this.atualizaDados();
        this.view.initUI();
        this.view.setVisible(true);
    }
    
     public MaximasMinimas getView() {
        return view;
    }

     private void atualizaDados() {
        DadosClimaticos maxTemperatura = new DadosClimaticos();
        DadosClimaticos minTemperatura = new DadosClimaticos();
        DadosClimaticos maxUmidade = new DadosClimaticos();
        DadosClimaticos minUmidade = new DadosClimaticos(); 
        DadosClimaticos maxPresao = new DadosClimaticos();
        DadosClimaticos minPresao = new DadosClimaticos();
        
         for(DadosClimaticos dado : listDadosClimaticos ) {
            if(maxTemperatura.getTemperatura() == null || dado.getTemperatura() > maxTemperatura.getTemperatura()) {
                maxTemperatura.setTemperatura(dado.getTemperatura());
                maxTemperatura.setData(dado.getData());
            }
            if(minTemperatura.getTemperatura() == null || dado.getTemperatura() < minTemperatura.getTemperatura()) {
                minTemperatura.setTemperatura(dado.getTemperatura());
                minTemperatura.setData(dado.getData());
            }
            
            if(maxUmidade.getUmidade() == null || dado.getUmidade() > maxUmidade.getUmidade()) {
                maxUmidade.setUmidade(dado.getUmidade());
                maxUmidade.setData(dado.getData());
            }
            if(minUmidade.getUmidade() == null || dado.getUmidade() < minUmidade.getUmidade()) {
                minUmidade.setUmidade(dado.getUmidade());
                minUmidade.setData(dado.getData());
            }
            
            if(maxPresao.getPresao() == null || dado.getPresao() > maxPresao.getPresao()) {
                maxPresao.setPresao(dado.getPresao());
                maxPresao.setData(dado.getData());
            }
            if(minPresao.getPresao() == null || dado.getPresao() < minPresao.getPresao()) {
                minPresao.setPresao(dado.getPresao());
                minPresao.setData(dado.getData());
            }
        }
        this.view.setMaxTemperatura(maxTemperatura);
        this.view.setMaxUmidade(maxUmidade);
        this.view.setMaxPresao(maxPresao);
        
        this.view.setMinTemperatura(minTemperatura);
        this.view.setMinUmidade(minUmidade);
        this.view.setMinPresao(minPresao);

     }
     
    @Override
    public void update(DadosClimaticos dadosCimaticos) {
        this.listDadosClimaticos.add(dadosCimaticos);
        this.atualizaDados();
        this.view.initUI();
    }
}

