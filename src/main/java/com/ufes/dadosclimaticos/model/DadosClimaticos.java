package com.ufes.dadosclimaticos.model;

import java.time.LocalDate;
import java.util.Objects;

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
    
     public DadosClimaticos(Double temperatura, Double umidade, Double presao, LocalDate data) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.presao = presao;
        this.data = data;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.temperatura);
        hash = 23 * hash + Objects.hashCode(this.umidade);
        hash = 23 * hash + Objects.hashCode(this.presao);
        hash = 23 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DadosClimaticos other = (DadosClimaticos) obj;
        if (!Objects.equals(this.temperatura, other.temperatura)) {
            return false;
        }
        if (!Objects.equals(this.umidade, other.umidade)) {
            return false;
        }
        if (!Objects.equals(this.presao, other.presao)) {
            return false;
        }
        return Objects.equals(this.data, other.data);
    }
    
    
}
