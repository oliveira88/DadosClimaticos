
package com.ufes.dadosclimaticos.logger;

import com.ufes.dadosclimaticos.logger.loggerAdaptado.XmlLoggerAdaptado;
import com.ufes.dadosclimaticos.model.DadosClimaticos;
import java.util.List;

public class XmlLogger implements ILogger{

    private final XmlLoggerAdaptado xmlLogger;
    private static XmlLogger xml; 
    
    private XmlLogger(String fileName) {
       
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

    public static XmlLogger SingletonXmlLogger(String fileName){
        if(xml == null){
          xml = new XmlLogger(fileName);
        }
        return xml;
    }
  
}
