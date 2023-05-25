package com.mycompany.projetodadosclimaticos.presenter;

import com.mycompany.projetodadosclimaticos.model.DadosClimaticos;
import com.mycompany.projetodadosclimaticos.model.observer.IObserver;
import com.mycompany.projetodadosclimaticos.util.ConvertDate;
import com.mycompany.projetodadosclimaticos.view.RegistrosDadosClimaticos;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RegistrosDadosClimaticosPresenter implements IObserver{

    private final RegistrosDadosClimaticos view;
    private List<DadosClimaticos> listDadosClimaticos;

    private DefaultTableModel table;

    public RegistrosDadosClimaticosPresenter(List<DadosClimaticos> listDadosClimaticos) {
        this.view = new RegistrosDadosClimaticos();
        this.listDadosClimaticos = listDadosClimaticos;

        this.initTableModel();

        this.loadTable();
        
        this.view.setVisible(true);

    }

    private void initTableModel() {
        this.table = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Data", "Temperatura", "Umidade", "Pressão"}
        );
        this.table.setNumRows(0);
        this.view.getJtblRegistros().setModel(table);

    }

    private void loadTable() {
        for (DadosClimaticos dadoClimatico : this.listDadosClimaticos) {
            this.table.addRow(new Object[]{
               ConvertDate.localDateToString( dadoClimatico.getData()),
                dadoClimatico.getTemperatura(),
                dadoClimatico.getUmidadae(),
                dadoClimatico.getPresao(),});
        }
    }

    @Override
    public void update(DadosClimaticos dadosCimaticos) {
        this.listDadosClimaticos.add(dadosCimaticos);
        this.loadTable();
    }

    public RegistrosDadosClimaticos getView() {
        return view;
    }

   
}
