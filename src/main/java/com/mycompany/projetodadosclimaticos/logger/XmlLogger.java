
package com.mycompany.projetodadosclimaticos.logger;

import com.mycompany.projetodadosclimaticos.logger.loggerAdaptado.XmlLoggerAdaptado;
import com.mycompany.projetodadosclimaticos.model.DadosClimaticos;
import java.util.List;

public class XmlLogger implements ILogger{

    private final XmlLoggerAdaptado xmlLogger;
    
    public XmlLogger(String fileName) {
        this.xmlLogger = new XmlLoggerAdaptado(fileName);
    }

    @Override
    public void logSalvar(DadosClimaticos dadosClimaticos) throws Exception{
        this.xmlLogger.gravarArquivo(dadosClimaticos);
    }

    @Override
    public List<DadosClimaticos> Logler() throws Exception {
        return this.xmlLogger.lersArquivo();
    }

  
}
