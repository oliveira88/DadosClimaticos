package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.DadosClimaticosObservable;
import com.ufes.dadosclimaticos.model.observer.IObservable;
import com.ufes.dadosclimaticos.model.observer.IObserver;
import com.ufes.dadosclimaticos.util.ConvertDate;
import com.ufes.dadosclimaticos.view.UltimaAtualizacaoTempoView;

public class UltimaAtualizacaoTempoPresenter implements IObserver {

    private final UltimaAtualizacaoTempoView view;
    private DadosClimaticosObservable dadosClimaticosObservable;

    public UltimaAtualizacaoTempoPresenter(DadosClimaticosObservable dadosClimaticosObservable) {
        this.dadosClimaticosObservable = dadosClimaticosObservable;
        this.view = new UltimaAtualizacaoTempoView();
        this.initAtualizacaoDadosClimaticos();
        this.view.setVisible(true);
    }

    private DadosClimaticos getDadosClimaitcoMaisRecente() {
        return this.dadosClimaticosObservable.getDadosList().stream().max((dadoUm, dadoDois) -> dadoUm.getData().compareTo(dadoDois.getData())).orElse(null);
    }

    private void initAtualizacaoDadosClimaticos() {
        DadosClimaticos dadosClimaticos = getDadosClimaitcoMaisRecente();
        if (dadosClimaticos != null) {
            this.view.getjLbTempValue().setText(dadosClimaticos.getTemperatura().toString());
            this.view.getjLbPreesaoValue().setText(dadosClimaticos.getPresao().toString());
            this.view.getjLbUmiValue().setText(dadosClimaticos.getUmidade().toString());
            this.view.getjLbData().setText(ConvertDate.localDateToString(dadosClimaticos.getData()));
        } else {
            this.view.getjLbTempValue().setText("");
            this.view.getjLbPreesaoValue().setText("");
            this.view.getjLbUmiValue().setText("");
            this.view.getjLbData().setText("");
        }
    }
    
    @Override
    public void update(IObservable observable) {
        if(observable instanceof DadosClimaticosObservable){
            this.dadosClimaticosObservable = (DadosClimaticosObservable) observable;
            initAtualizacaoDadosClimaticos();
        }
    }
    
    public UltimaAtualizacaoTempoView getView() {
        return view;
    }
}
