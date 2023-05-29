package com.mycompany.projetodadosclimaticos.presenter;

import com.mycompany.projetodadosclimaticos.logger.ILogger;
import com.mycompany.projetodadosclimaticos.logger.XmlLogger;
import com.mycompany.projetodadosclimaticos.model.DadosClimaticos;
import com.mycompany.projetodadosclimaticos.service.EstacaoClimaticaService;
import com.mycompany.projetodadosclimaticos.view.MainScreen;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class MainScreenPresenter {
    private final MainScreen viewMain;
    private final CadastroDadosClimaticosPresenter viewCadastroDadosClimaticosPresenter;
    private final UltimaAtualizacaoTempoPresenter ultimaAtualizacaoTempoPresenter;
    private final RegistrosDadosClimaticosPresenter viewRegistros;
    private final ILogger log;
    private final EstacaoClimaticaService estacaoService;
    
    public MainScreenPresenter() throws Exception {
        
        this.viewMain = new MainScreen();
        this.log = new XmlLogger("C:\\Users\\itix\\Documents\\dadosClimaticos.xml");
        this.estacaoService = new EstacaoClimaticaService(this.log);
        this.viewRegistros = new RegistrosDadosClimaticosPresenter(getListDadosClimaticosLogger());
        this.ultimaAtualizacaoTempoPresenter = new UltimaAtualizacaoTempoPresenter(getListDadosClimaticosLogger());
        initEstacaoClimaticaService();
        
        this.viewCadastroDadosClimaticosPresenter = new CadastroDadosClimaticosPresenter(this.estacaoService);
    
        configView();
        initPanel();
            
        
        
        this.viewMain.setVisible(true);

        
        
    }

    
    private void initPanel(){
        this.viewMain.getjDesktopPane1().add(viewCadastroDadosClimaticosPresenter.getView());
        this.viewMain.getjDesktopPane1().add(viewRegistros.getView());
        this.viewMain.getjDesktopPane1().add(ultimaAtualizacaoTempoPresenter.getView());  
    }
    
    private void initEstacaoClimaticaService(){
        this.estacaoService.addObserver(this.viewRegistros );
        this.estacaoService.addObserver(this.ultimaAtualizacaoTempoPresenter);
    }

    private void configView(){
        viewMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private List<DadosClimaticos> getListDadosClimaticosLogger(){
        try {
            return  this.log.Logler();
        } catch (Exception ex) {
            Logger.getLogger(MainScreenPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
