
package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.IObserver;
import com.ufes.dadosclimaticos.view.DadosMedios;
import java.text.DecimalFormat;
import java.util.List;


public class DadosMediosPresenter implements IObserver{
    private DadosMedios view;
    private List<DadosClimaticos> listDadosClimaticos;
    public DadosMediosPresenter(List<DadosClimaticos> listDadosClimaticos) {
        this.listDadosClimaticos = listDadosClimaticos;
        this.view = new DadosMedios();
        initAtualizacaoDadosMedios();
        this.view.setVisible(true);
    }
    private DadosClimaticos getDadosAtualizacaoMedio() {
        Double avgTemperatura = this.listDadosClimaticos.stream().mapToDouble(DadosClimaticos::getTemperatura).average().orElse(0.0);
        Double avgUmi = this.listDadosClimaticos.stream().mapToDouble(DadosClimaticos::getUmidade).average().orElse(0.0);
        Double avgPress = this.listDadosClimaticos.stream().mapToDouble(DadosClimaticos::getPresao).average().orElse(0.0);
        return new DadosClimaticos(avgTemperatura,avgUmi, avgPress);
    }

    private void initAtualizacaoDadosMedios() {
        DadosClimaticos dadosClimaticos = getDadosAtualizacaoMedio();
        
        if (dadosClimaticos != null) {
            this.view.getjLbTempValue().setText(new DecimalFormat("#.##").format(dadosClimaticos.getTemperatura()));
            this.view.getjLbPreesaoValue().setText(new DecimalFormat("#.##").format(dadosClimaticos.getPresao()));
            this.view.getjLbUmiValue().setText(new DecimalFormat("#.##").format(dadosClimaticos.getUmidade()));
            this.view.getjLbNrRegistros().setText(String.valueOf(this.listDadosClimaticos.size()));
        } else {
            this.view.getjLbTempValue().setText("");
            this.view.getjLbPreesaoValue().setText("");
            this.view.getjLbUmiValue().setText("");
            this.view.getjLbNrRegistros().setText("");
        }

    }
    public DadosMedios getView() {
        return view;
    }

    @Override
    public void update(DadosClimaticos dadosCimaticos) {
       this.listDadosClimaticos.add(dadosCimaticos);
       this.initAtualizacaoDadosMedios();
    }
}
