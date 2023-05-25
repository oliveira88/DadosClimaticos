package com.ufes.dadosclimaticos.model;

import com.ufes.dadosclimaticos.model.observer.IObserver;
import com.ufes.dadosclimaticos.model.subject.ISubject;
import java.time.LocalDate;
import java.util.List;

public class DadosClimaticos {

    private Double temperatura;
    private Double umidadae;
    private Double presao;
    private LocalDate data;

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getUmidadae() {
        return umidadae;
    }

    public void setUmidadae(Double umidadae) {
        this.umidadae = umidadae;
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
