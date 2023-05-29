package com.ufes.dadosclimaticos.util;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.data.category.CategoryDataset;


public class CustomLabelGenerator implements CategoryItemLabelGenerator {
    private final DadosClimaticos maxTemperatura;
    private final DadosClimaticos minTemperatura;
    private final DadosClimaticos maxUmidade;
    private final DadosClimaticos minUmidade; 
    private final DadosClimaticos maxPresao;
    private final DadosClimaticos minPresao;

    public CustomLabelGenerator(DadosClimaticos maxTemperatura, DadosClimaticos minTemperatura, DadosClimaticos maxUmidade, DadosClimaticos minUmidade, DadosClimaticos maxPresao, DadosClimaticos minPresao) {
        this.maxTemperatura = maxTemperatura;
        this.minTemperatura = minTemperatura;
        this.maxUmidade = maxUmidade;
        this.minUmidade = minUmidade;
        this.maxPresao = maxPresao;
        this.minPresao = minPresao;
    }
   
    
    @Override
    public String generateLabel(CategoryDataset dataset, int row, int column) {
        if(dataset.getRowKey(row).equals("Max")){
            if(dataset.getColumnKey(column).equals("Temperatura")){
                return ConvertDate.localDateToString(maxTemperatura.getData());
            }
            if(dataset.getColumnKey(column).equals("Umidade")){
                return ConvertDate.localDateToString(maxUmidade.getData());
            }
            if(dataset.getColumnKey(column).equals("Pressão")){
                return ConvertDate.localDateToString(maxPresao.getData());
            }
        }
        else if(dataset.getRowKey(row).equals("Min")){
           if(dataset.getColumnKey(column).equals("Temperatura")){
                return ConvertDate.localDateToString(minTemperatura.getData());
            }
            if(dataset.getColumnKey(column).equals("Umidade")){
                return ConvertDate.localDateToString(minUmidade.getData());
            }
            if(dataset.getColumnKey(column).equals("Pressão")){
                return ConvertDate.localDateToString(minPresao.getData());
            }
        }
        return null;
    }

    @Override
    public String generateRowLabel(CategoryDataset cd, int i) {
        return null;
    }

    @Override
    public String generateColumnLabel(CategoryDataset cd, int i) {
        return null;
    }
}
