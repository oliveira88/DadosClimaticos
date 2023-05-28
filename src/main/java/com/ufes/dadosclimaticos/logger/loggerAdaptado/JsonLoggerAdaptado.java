package com.ufes.dadosclimaticos.logger.loggerAdaptado;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ufes.dadosclimaticos.model.DadosClimaticos;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonLoggerAdaptado {

    private final String arquivoPath;

    public JsonLoggerAdaptado(String arquivoPath) {
        this.arquivoPath = arquivoPath;
    }

    public void gravarArquivo(DadosClimaticos dadosClimaticos) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        List<DadosClimaticos> dadosExistentes = new ArrayList<>();
        List<DadosClimaticos> dados = new ArrayList<>();

        File file = new File(this.arquivoPath);
        
        if (file.exists() && file.length() > 0) {
            dadosExistentes = objectMapper.readValue(file, new TypeReference<List<DadosClimaticos>>() {
            });
        }
        if (dadosExistentes == null && dadosExistentes.isEmpty()) {
            dados.add(dadosClimaticos);
            objectMapper.writeValue(new File(this.arquivoPath), dados);
            
        } else {
            dadosExistentes.add(dadosClimaticos);
            objectMapper.writeValue(new File(this.arquivoPath), dadosExistentes);
        }

    }

    public List<DadosClimaticos> getDadosArquivo() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        List<DadosClimaticos> dadosClimaticos = new ArrayList<>();
        File file = new File(this.arquivoPath);
        dadosClimaticos = objectMapper.readValue(file, new TypeReference<List<DadosClimaticos>>() {
        });
        
        return dadosClimaticos;
    }

}
