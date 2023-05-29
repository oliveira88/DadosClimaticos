package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.DadosClimaticosObservable;
import com.ufes.dadosclimaticos.model.observer.IObservable;
import com.ufes.dadosclimaticos.model.observer.IObserver;
import com.ufes.dadosclimaticos.service.EstacaoClimaticaService;
import com.ufes.dadosclimaticos.util.ConvertDate;
import com.ufes.dadosclimaticos.view.RegistrosDadosClimaticosView;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RegistrosDadosClimaticosPresenter implements IObserver {

    private final RegistrosDadosClimaticosView view;
    private final EstacaoClimaticaService estacaoClimaticaService;
    private DefaultTableModel tableModel;

    public RegistrosDadosClimaticosPresenter(EstacaoClimaticaService estacaoClimaticaService) {
        this.estacaoClimaticaService = estacaoClimaticaService;
        this.view = new RegistrosDadosClimaticosView();
        this.initTableModel();
        this.initActions();
        this.loadTable();
        this.view.setVisible(true);
    }

    private void initTableModel() {
        this.tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Data", "Temperatura", "Umidade", "Pressão"}
        );
        this.tableModel.setNumRows(0);
        this.view.getJtblRegistros().setModel(tableModel);

    }
    
    private void initActions() {
        this.view.getjBtnRemover().addActionListener((ActionEvent ae) -> {
            this.removerDados();
        });
    }
    
     private void checarSelecionado() throws Exception {
         int index =  this.view.getJtblRegistros().getSelectedRow();
         if(index == -1){
             throw new Exception("É necessário selecionar um dado para remover!");
         }
     }
     
     private void removerDados() {
        try {
            this.checarSelecionado();
            this.estacaoClimaticaService.removerDadosClimaticos(this.obterDadoSelecionadoTable());
            JOptionPane.showMessageDialog(view, "Dados climáticos removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Remover dados climáticos", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    
     
    private DadosClimaticos obterDadoSelecionadoTable() throws Exception {
         String data = this.view.getJtblRegistros().getValueAt(this.view.getJtblRegistros().getSelectedRow(), 0).toString();
         String temperatura = this.view.getJtblRegistros().getValueAt(this.view.getJtblRegistros().getSelectedRow(), 1).toString();
         String umidade = this.view.getJtblRegistros().getValueAt(this.view.getJtblRegistros().getSelectedRow(), 2).toString();
         String pressao = this.view.getJtblRegistros().getValueAt(this.view.getJtblRegistros().getSelectedRow(), 3).toString();

         return new DadosClimaticos(
            Double.valueOf(temperatura),
            Double.valueOf(umidade),
            Double.valueOf(pressao),
            ConvertDate.stringToLocalDate(data)
        );
         
     }
    
    private void clearTable() {
        this.tableModel.setRowCount(0);
    }
    
    private void loadTable() {
        if (this.estacaoClimaticaService.getDadosClimaticosObservable().getDadosList() != null) {
            for (DadosClimaticos dadoClimatico : this.estacaoClimaticaService.getDadosClimaticosObservable().getDadosList()) {
                this.tableModel.addRow(new Object[]{
                    ConvertDate.localDateToString(dadoClimatico.getData()),
                    dadoClimatico.getTemperatura(),
                    dadoClimatico.getUmidade(),
                    dadoClimatico.getPresao(),});
            }
        }

    }

    public RegistrosDadosClimaticosView getView() {
        return view;
    }

    @Override
    public void update(IObservable observable) {
        if(observable instanceof DadosClimaticosObservable){
            this.estacaoClimaticaService.setDadosClimaticosObservable((DadosClimaticosObservable) observable);
            this.clearTable();
            this.loadTable();
        }
    }
}
