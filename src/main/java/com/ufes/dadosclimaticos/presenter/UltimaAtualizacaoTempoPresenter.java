package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.IObserver;
import com.ufes.dadosclimaticos.util.ConvertDate;
import com.ufes.dadosclimaticos.view.UltimaAtualizacaoTempo;
import java.util.List;

public class UltimaAtualizacaoTempoPresenter implements IObserver{
    
    private final UltimaAtualizacaoTempo view;
    private List<DadosClimaticos> listDadosClimaticos;


    public UltimaAtualizacaoTempoPresenter(List<DadosClimaticos> listDadosClimaticos) {
        this.view = new UltimaAtualizacaoTempo();
        
        this.listDadosClimaticos = listDadosClimaticos;

        initAtualizacaoDadosClimaticos();
        
        this.view.setVisible(true);

        
    }
    
    private DadosClimaticos getDadosClimaitcoMaisRecente(){
     return this.listDadosClimaticos.stream().max((dadoUm, dadoDois) -> dadoUm.getData().compareTo(dadoDois.getData())).orElse(null);
    }
    
    private void initAtualizacaoDadosClimaticos(){
        DadosClimaticos dadosClimaticos = getDadosClimaitcoMaisRecente();
        
        this.view.getjLbTempValue().setText(dadosClimaticos.getPresao().toString());
        this.view.getjLbPreesaoValue().setText(dadosClimaticos.getPresao().toString());
        this.view.getjLbUmiValue().setText(dadosClimaticos.getPresao().toString());
        this.view.getjLbData().setText(ConvertDate.localDateToString(dadosClimaticos.getData()));
    
    }
    
    @Override
    public void update(DadosClimaticos dadosCimaticos) {
        this.listDadosClimaticos.add(dadosCimaticos);
        initAtualizacaoDadosClimaticos();
    }

    public UltimaAtualizacaoTempo getView() {
        return view;
    }




}
