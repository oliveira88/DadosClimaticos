package com.mycompany.projetodadosclimaticos.presenter;

import com.mycompany.projetodadosclimaticos.logger.ILogger;
import com.mycompany.projetodadosclimaticos.logger.JsonLooger;
import com.mycompany.projetodadosclimaticos.logger.XmlLogger;
import com.mycompany.projetodadosclimaticos.model.DadosClimaticos;
import com.mycompany.projetodadosclimaticos.service.EstacaoClimaticaService;
import com.mycompany.projetodadosclimaticos.view.MainScreen;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class MainScreenPresenter {

    private MainScreen viewMain;
    private CadastroDadosClimaticosPresenter viewCadastroDadosClimaticosPresenter;
    private UltimaAtualizacaoTempoPresenter ultimaAtualizacaoTempoPresenter;
    private RegistrosDadosClimaticosPresenter viewRegistros;
    private ILogger log;
    private EstacaoClimaticaService estacaoService;
    private SistemaLogPresenter sistemaLogPresenter;
    private String tipoSelecionado = "XML";

    public MainScreenPresenter() {
        this.initMain("XML");
    }

    public void initMain(String ultimoTipoSelecioando) {
        this.viewMain = new MainScreen();
        this.setTipoSelecionado(ultimoTipoSelecioando);
        configView();

        this.log = getInstanciaLogger();
        if(this.log == null) return;
        
        this.initPreseters();
        initPanel();
        initAction();

        this.viewMain.setVisible(true);
    }

    private void initPreseters() {
        this.estacaoService = new EstacaoClimaticaService(this.log);
        this.viewRegistros = new RegistrosDadosClimaticosPresenter(getListDadosClimaticosLogger());
        this.ultimaAtualizacaoTempoPresenter = new UltimaAtualizacaoTempoPresenter(getListDadosClimaticosLogger());
        initEstacaoClimaticaService();

        this.viewCadastroDadosClimaticosPresenter = new CadastroDadosClimaticosPresenter(this.estacaoService);
    }

    private void initPanel() {
        this.viewMain.getjDesktopPane1().add(viewCadastroDadosClimaticosPresenter.getView());
        this.viewMain.getjDesktopPane1().add(viewRegistros.getView());
        this.viewMain.getjDesktopPane1().add(ultimaAtualizacaoTempoPresenter.getView());
    }

    private void initEstacaoClimaticaService() {
        this.estacaoService.addObserver(this.viewRegistros);
        this.estacaoService.addObserver(this.ultimaAtualizacaoTempoPresenter);
    }

    private void configView() {
        viewMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private List<DadosClimaticos> getListDadosClimaticosLogger() {
        try {
            if (this.log.Logler() != null) {
                return this.log.Logler();
            }

        } catch (Exception ex) {
            return new ArrayList<>();
        }
        return null;
    }

    public MainScreen getViewMain() {
        return viewMain;
    }

    private void initAction() {
        this.viewMain.getjMenuItem().addActionListener((ActionEvent ae) -> {
            this.sistemaLogPresenter = new SistemaLogPresenter(this, this.tipoSelecionado);
        });
    }

    public void setTipoSelecionado(String tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
    }
    
    
    
    private ILogger getInstanciaLogger(){
        if("XML".equalsIgnoreCase(this.tipoSelecionado)){
            return XmlLogger.SingletonXmlLogger("dadosClimaticoss.xml");
        }
        if("JSON".equalsIgnoreCase(this.tipoSelecionado)){
            return JsonLooger.SingletonJsonLooger("dadosClimaticos.json");
        }
        return null;
    }
    

}
