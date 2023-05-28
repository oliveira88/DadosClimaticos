package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.logger.ILogger;
import com.ufes.dadosclimaticos.logger.JsonLogger;
import com.ufes.dadosclimaticos.logger.XmlLogger;
import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.service.EstacaoClimaticaService;
import com.ufes.dadosclimaticos.view.MainScreen;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class MainScreenPresenter {

    private MainScreen viewMain;
    private CadastroDadosClimaticosPresenter cadastroDadosClimaticosPresenter;
    private UltimaAtualizacaoTempoPresenter ultimaAtualizacaoTempoPresenter;
    private RegistrosDadosClimaticosPresenter registrosDadosClimaticosPresenter;
    private DadosMediosPresenter dadosMediosPresenter;
    private MaximasMinimasPresenter maximasMinimasPresenter;
    private ILogger log;
    private EstacaoClimaticaService estacaoClimaticaService;
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
        
        this.initPresenters();
        initPanel();
        initAction();

        this.viewMain.setVisible(true);
    }

    private void initPresenters() {
        this.estacaoClimaticaService = new EstacaoClimaticaService(this.log);
        this.registrosDadosClimaticosPresenter = new RegistrosDadosClimaticosPresenter(getListDadosClimaticosLogger());
        this.ultimaAtualizacaoTempoPresenter = new UltimaAtualizacaoTempoPresenter(getListDadosClimaticosLogger());
        initEstacaoClimaticaService();
        this.cadastroDadosClimaticosPresenter = new CadastroDadosClimaticosPresenter(this.estacaoClimaticaService);
        this.maximasMinimasPresenter = new MaximasMinimasPresenter(getListDadosClimaticosLogger());
        this.dadosMediosPresenter = new DadosMediosPresenter();
    }

    private void initPanel() {
        this.viewMain.getjDesktopPane1().add(cadastroDadosClimaticosPresenter.getView());
        this.viewMain.getjDesktopPane1().add(registrosDadosClimaticosPresenter.getView());
        this.viewMain.getjDesktopPane1().add(ultimaAtualizacaoTempoPresenter.getView());
        this.viewMain.getjDesktopPane1().add(dadosMediosPresenter.getView());
        this.viewMain.getjDesktopPane1().add(maximasMinimasPresenter.getView());
                
    }

    private void initEstacaoClimaticaService() {
        this.estacaoClimaticaService.addObserver(this.registrosDadosClimaticosPresenter);
        this.estacaoClimaticaService.addObserver(this.ultimaAtualizacaoTempoPresenter);
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
        this.viewMain.getjMenuItem().addActionListener(ae -> 
            this.sistemaLogPresenter = new SistemaLogPresenter(this, this.tipoSelecionado)
        );
    }

    public void setTipoSelecionado(String tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
    }
    
    private ILogger getInstanciaLogger(){
        if("XML".equalsIgnoreCase(this.tipoSelecionado)){
            return XmlLogger.SingletonXmlLogger("dadosClimaticoss.xml");
        }
        if("JSON".equalsIgnoreCase(this.tipoSelecionado)){
            return JsonLogger.SingletonJsonLogger("dadosClimaticos.json");
        }
        return null;
    }
}
