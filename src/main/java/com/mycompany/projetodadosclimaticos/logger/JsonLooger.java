package com.mycompany.projetodadosclimaticos.logger;

import com.mycompany.projetodadosclimaticos.logger.loggerAdaptado.JsonLoggerAdaptado;
import com.mycompany.projetodadosclimaticos.model.DadosClimaticos;
import java.util.List;

public class JsonLooger implements ILogger{
    
    private final JsonLoggerAdaptado jsonLogger;
    private static JsonLooger json;

    public JsonLooger(String fileName) {
        this.jsonLogger = new JsonLoggerAdaptado(fileName);
    }
    
    
    @Override
    public void logSalvar(DadosClimaticos dadosClimaticos) throws Exception {
        this.jsonLogger.gravarArquivo(dadosClimaticos);
    }

    @Override
    public List<DadosClimaticos> Logler() throws Exception {
        return this.jsonLogger.getDadosArquivo();
    }
   
    public static JsonLooger SingletonJsonLooger (String fileName){
        if(json == null){
            json = new JsonLooger(fileName);
        }
        return json;
    }
}
