package com.ufes.dadosclimaticos.logger;

import com.ufes.dadosclimaticos.logger.loggerAdaptado.JsonLoggerAdaptado;
import com.ufes.dadosclimaticos.model.DadosClimaticos;
import java.util.List;

public class JsonLogger implements ILogger{
    
    private final JsonLoggerAdaptado jsonLogger;
    private static JsonLogger json;

    public JsonLogger(String fileName) {
        this.jsonLogger = new JsonLoggerAdaptado(fileName);
    }
    
    
    @Override
    public void salvar(DadosClimaticos dadosClimaticos) throws Exception {
        this.jsonLogger.gravarArquivo(dadosClimaticos);
    }

    @Override
    public void remover(DadosClimaticos dadosClimaticos) throws Exception {
        this.jsonLogger.removerDadoArquivo(dadosClimaticos);
    }
    
    @Override
    public List<DadosClimaticos> ler() throws Exception {
        return this.jsonLogger.getDadosArquivo();
    }
   
    public static JsonLogger SingletonJsonLogger (String fileName){
        if(json == null){
            json = new JsonLogger(fileName);
        }
        return json;
    }

    
}
