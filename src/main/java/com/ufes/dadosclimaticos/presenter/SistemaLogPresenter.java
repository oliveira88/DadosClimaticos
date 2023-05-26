package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.view.SistemaLog;
import java.awt.event.ActionEvent;

public class SistemaLogPresenter {
    
    private String tipo;
    private final SistemaLog view;
    private MainScreenPresenter mainView;
    public SistemaLogPresenter(MainScreenPresenter mainView, String tipo) {
        this.view = new SistemaLog();
        this.tipo = tipo;
        this.mainView = mainView;
        
       this.mainView.getViewMain().getjDesktopPane1().add(view);
       this.view.getjComboBox1().setSelectedItem(this.tipo);
       initAction();
       view.setVisible(true);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    private void initAction(){
        this.view.getjButton1().addActionListener((ActionEvent ae) -> {
            this.mainView.setTipoSelecionado(this.view.getjComboBox1().getSelectedItem().toString());
            this.mainView.getViewMain().dispose();
            this.mainView.initMain(this.view.getjComboBox1().getSelectedItem().toString());
        });
    }


}
