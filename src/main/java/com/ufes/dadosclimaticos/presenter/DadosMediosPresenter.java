
package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.DadosClimaticosObservable;
import com.ufes.dadosclimaticos.model.observer.IObservable;
import com.ufes.dadosclimaticos.model.observer.IObserver;
import com.ufes.dadosclimaticos.view.DadosMediosView;
import java.text.DecimalFormat;


public class DadosMediosPresenter implements IObserver {
    private final DadosMediosView view;
    private DadosClimaticosObservable dadosClimaticosObservable;
    
    public DadosMediosPresenter(DadosClimaticosObservable dadosClimaticosObservable) {
        this.dadosClimaticosObservable = dadosClimaticosObservable;
        this.view = new DadosMediosView();
        this.initAtualizacaoDadosMedios();
        this.view.setVisible(true);
    }
    
    private DadosClimaticos getDadosAtualizacaoMedio() {
        Double avgTemperatura = this.dadosClimaticosObservable.getDadosList().stream().mapToDouble(DadosClimaticos::getTemperatura).average().orElse(0.0);
        Double avgUmi = this.dadosClimaticosObservable.getDadosList().stream().mapToDouble(DadosClimaticos::getUmidade).average().orElse(0.0);
        Double avgPress = this.dadosClimaticosObservable.getDadosList().stream().mapToDouble(DadosClimaticos::getPresao).average().orElse(0.0);
        return new DadosClimaticos(avgTemperatura,avgUmi, avgPress);
    }

    private void initAtualizacaoDadosMedios() {
        DadosClimaticos dadosClimaticos = getDadosAtualizacaoMedio();
        
        if (dadosClimaticos != null) {
            this.view.getjLbTempValue().setText(new DecimalFormat("#.##").format(dadosClimaticos.getTemperatura()));
            this.view.getjLbPreesaoValue().setText(new DecimalFormat("#.##").format(dadosClimaticos.getPresao()));
            this.view.getjLbUmiValue().setText(new DecimalFormat("#.##").format(dadosClimaticos.getUmidade()));
            this.view.getjLbNrRegistros().setText(String.valueOf(this.dadosClimaticosObservable.getDadosList().size()));
        } else {
            this.view.getjLbTempValue().setText("");
            this.view.getjLbPreesaoValue().setText("");
            this.view.getjLbUmiValue().setText("");
            this.view.getjLbNrRegistros().setText("");
        }

    }
    
    public DadosMediosView getView() {
        return view;
    }

    @Override
    public void update(IObservable observable) {
        if(observable instanceof DadosClimaticosObservable){
            this.dadosClimaticosObservable = (DadosClimaticosObservable) observable;
            this.initAtualizacaoDadosMedios();
        }
    }
}
