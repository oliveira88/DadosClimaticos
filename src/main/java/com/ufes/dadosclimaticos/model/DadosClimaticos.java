package com.ufes.dadosclimaticos.model;

import java.time.LocalDate;

public class DadosClimaticos {

    private Double temperatura;
    private Double umidade;
    private Double presao;
    private LocalDate data;

    public DadosClimaticos() {
    }

    public DadosClimaticos(Double temperatura, Double umidade, Double presao) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.presao = presao;
    }
    
    public Double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public Double getPresao() {
        return presao;
    }

    public void setPresao(Double presao) {
        this.presao = presao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
