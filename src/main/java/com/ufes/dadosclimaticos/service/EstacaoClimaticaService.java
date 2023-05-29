package com.ufes.dadosclimaticos.service;

import com.ufes.dadosclimaticos.logger.ILogger;
import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.DadosClimaticosObservable;
import java.util.ArrayList;
import java.util.List;


public final class EstacaoClimaticaService {
    private final ILogger log;
    private DadosClimaticosObservable dadosClimaticosObservable;

    public EstacaoClimaticaService(ILogger log)  {
        this.log = log;
        this.dadosClimaticosObservable = new DadosClimaticosObservable(this.lerDadosClimaticos());
    }    
    
    public void salvarDadosClimaticos(DadosClimaticos dadoClimatico) throws Exception {
        this.log.salvar(dadoClimatico);
        this.dadosClimaticosObservable.addDados(dadoClimatico);
    }
    
    public void removerDadosClimaticos(DadosClimaticos dadoClimatico) throws Exception {
//        this.log.remover(dadoClimatico);
        this.dadosClimaticosObservable.removeDados(dadoClimatico);
    }
    
    public List<DadosClimaticos> lerDadosClimaticos() {
        try {
            if (this.log.ler() != null) {
                return this.log.ler();
            }
        } catch (Exception ex) {
            return new ArrayList<>();
        }
        return null;
    } 
    
    public DadosClimaticosObservable getDadosClimaticosObservable() {
        return dadosClimaticosObservable;
    }

    public void setDadosClimaticosObservable(DadosClimaticosObservable dadosClimaticosObservable) {
        this.dadosClimaticosObservable = dadosClimaticosObservable;
    }
}
