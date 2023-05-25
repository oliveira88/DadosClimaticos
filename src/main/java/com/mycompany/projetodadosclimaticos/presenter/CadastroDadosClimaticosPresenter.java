package com.mycompany.projetodadosclimaticos.presenter;

import com.mycompany.projetodadosclimaticos.model.DadosClimaticos;
import com.mycompany.projetodadosclimaticos.service.EstacaoClimaticaService;
import com.mycompany.projetodadosclimaticos.util.ConvertDate;
import com.mycompany.projetodadosclimaticos.view.CadastroDadosClimaticos;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class CadastroDadosClimaticosPresenter {

    private final CadastroDadosClimaticos view;
    private final EstacaoClimaticaService estacaoClimaticaService;

    public CadastroDadosClimaticosPresenter(EstacaoClimaticaService estacaoClimaticaService) {
        view = new CadastroDadosClimaticos();

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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Slvar Dados climáticos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private DadosClimaticos loadDadosClimaticos() throws Exception {
        this.checkFieldsIsEmpty();
        DadosClimaticos dadoClimatico = new DadosClimaticos();
        dadoClimatico.setTemperatura(Double.parseDouble(this.view.getjTextTemperatura().getText()));
        dadoClimatico.setPresao(Double.parseDouble(this.view.getjTextPressao().getText()));
        dadoClimatico.setUmidadae(Double.parseDouble(this.view.getJtxtUmidade().getText()));
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

    private boolean fieldsIsEmpty() {
        return view.getJtxtData().getText().replaceAll("\\s", "").equals("//")
                || view.getjTextPressao().getText().equals("")
                || view.getJtxtUmidade().getText().equals("")
                || view.getjTextTemperatura().getText().equals("");

    }

    private void checkFieldsIsEmpty() throws Exception {
        if (fieldsIsEmpty()) {
            throw new Exception("Todos os campos devem ser preenchidos!");
        }

    }

    public CadastroDadosClimaticos getView() {
        return view;
    }
    

}
