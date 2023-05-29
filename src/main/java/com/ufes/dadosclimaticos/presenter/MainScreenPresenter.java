package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.logger.ILogger;
import com.ufes.dadosclimaticos.logger.JsonLogger;
import com.ufes.dadosclimaticos.logger.XmlLogger;
import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.model.observer.DadosClimaticosObservable;
import com.ufes.dadosclimaticos.model.observer.IObservable;
import com.ufes.dadosclimaticos.model.observer.IObserver;
import com.ufes.dadosclimaticos.service.EstacaoClimaticaService;
import com.ufes.dadosclimaticos.view.MainScreenView;

import javax.swing.JFrame;

public class MainScreenPresenter implements IObserver {

    private MainScreenView viewMain;
    private CadastroDadosClimaticosPresenter cadastroDadosClimaticosPresenter;
    private UltimaAtualizacaoTempoPresenter ultimaAtualizacaoTempoPresenter;
    private RegistrosDadosClimaticosPresenter registrosDadosClimaticosPresenter;
    private DadosMediosPresenter dadosMediosPresenter;
    private MaximasMinimasPresenter maximasMinimasPresenter;
    private ILogger log;
    private EstacaoClimaticaService estacaoClimaticaService;
    private String tipoSelecionado = "XML";

    public MainScreenPresenter() {
        this.initMain("XML");
    }

    public void initMain(String ultimoTipoSelecioando) {
        this.viewMain = new MainScreenView();
        this.setTipoSelecionado(ultimoTipoSelecioando);
        this.configView();

        this.log = getInstanciaLogger();
        if(this.log == null) return;
        
        this.initPresenters();
        this.initPanel();
        this.initAction();
        this.ajusteQtdRegistro();
        this.viewMain.setVisible(true);
    }

    private void initPresenters() {
        this.estacaoClimaticaService = new EstacaoClimaticaService(this.log);
        this.registrosDadosClimaticosPresenter = new RegistrosDadosClimaticosPresenter(this.estacaoClimaticaService);
        this.ultimaAtualizacaoTempoPresenter = new UltimaAtualizacaoTempoPresenter(this.estacaoClimaticaService.getDadosClimaticosObservable());
        this.maximasMinimasPresenter = new MaximasMinimasPresenter(this.estacaoClimaticaService.getDadosClimaticosObservable());
        this.dadosMediosPresenter = new DadosMediosPresenter(this.estacaoClimaticaService.getDadosClimaticosObservable());
        this.cadastroDadosClimaticosPresenter = new CadastroDadosClimaticosPresenter(this.estacaoClimaticaService);
        this.initObservers();
    }

    private void initPanel() {
        this.viewMain.getjDesktopPane1().add(cadastroDadosClimaticosPresenter.getView());
        this.viewMain.getjDesktopPane1().add(registrosDadosClimaticosPresenter.getView());
        this.viewMain.getjDesktopPane1().add(ultimaAtualizacaoTempoPresenter.getView());
        this.viewMain.getjDesktopPane1().add(dadosMediosPresenter.getView());
        this.viewMain.getjDesktopPane1().add(maximasMinimasPresenter.getView());
                
    }

    private void initObservers() {
        this.estacaoClimaticaService.getDadosClimaticosObservable().addObserver(
            this.registrosDadosClimaticosPresenter,
            this.ultimaAtualizacaoTempoPresenter,
            this.maximasMinimasPresenter,
            this.dadosMediosPresenter,
            this
        );
    }

    private void configView() {
        viewMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public MainScreenView getViewMain() {
        return viewMain;
    }

    private void initAction() {
        this.viewMain.getjMenuItem().addActionListener(ae -> 
                new SistemaLogPresenter(this, this.tipoSelecionado)
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
    
    private void ajusteQtdRegistro() {
        int qtdRegistro = this.estacaoClimaticaService.getDadosClimaticosObservable().getDadosList().size();
        this.viewMain.getjLbNrRegistro().setText(String.valueOf(qtdRegistro));
    }
    
    @Override
    public void update(IObservable observable) {
        if(observable instanceof DadosClimaticosObservable){
            this.estacaoClimaticaService.setDadosClimaticosObservable((DadosClimaticosObservable)observable);
            this.ajusteQtdRegistro();
        }
    }
}
