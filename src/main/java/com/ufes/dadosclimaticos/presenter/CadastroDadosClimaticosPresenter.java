package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.service.EstacaoClimaticaService;
import com.ufes.dadosclimaticos.util.ConvertDate;
import com.ufes.dadosclimaticos.view.CadastroDadosClimaticosView;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class CadastroDadosClimaticosPresenter {

    private final CadastroDadosClimaticosView view;
    private final EstacaoClimaticaService estacaoClimaticaService;

    public CadastroDadosClimaticosPresenter(EstacaoClimaticaService estacaoClimaticaService) {
        view = new CadastroDadosClimaticosView();

        this.estacaoClimaticaService = estacaoClimaticaService;
        this.initActions();
        this.view.setVisible(true);
    }

    private void initActions() {
        this.view.getJbSalva().addActionListener((ActionEvent ae) -> {
            this.salvarDados();
        });
    }

    private void salvarDados() {
        try {
            this.estacaoClimaticaService.salvarDadosClimaticos(this.loadDadosClimaticos());
            JOptionPane.showMessageDialog(view, "Dados climáticos inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Salvar Dados climáticos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private DadosClimaticos loadDadosClimaticos() throws Exception {
        this.checkFieldsIsEmpty();
        DadosClimaticos dadoClimatico = new DadosClimaticos();
        dadoClimatico.setTemperatura(Double.valueOf(this.view.getjTextTemperatura().getText()));
        dadoClimatico.setPresao(Double.valueOf(this.view.getjTextPressao().getText()));
        dadoClimatico.setUmidade(Double.valueOf(this.view.getJtxtUmidade().getText()));
        dadoClimatico.setData(getDateOfTextField());
        return dadoClimatico;
    }

    private LocalDate getDateOfTextField() throws Exception {
        LocalDate localDate = ConvertDate.stringToLocalDate(this.view.getJtxtData().getText());

        if (localDate.isAfter(LocalDate.now())) {
            throw new Exception("Data inválida, por favor insira uma data anterior ao dia de hoje!");
        }
        return localDate;    
    }

    private void checkFieldsIsEmpty() throws Exception {
        if (view.getJtxtData().getText().replaceAll("\\s", "").equals("//")
                || view.getjTextPressao().getText().equals("")
                || view.getJtxtUmidade().getText().equals("")
                || view.getjTextTemperatura().getText().equals("")) {
            throw new Exception("Todos os campos devem ser preenchidos!");
        }

    }

    public CadastroDadosClimaticosView getView() {
        return view;
    }
    

}
