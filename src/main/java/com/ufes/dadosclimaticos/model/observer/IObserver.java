package com.ufes.dadosclimaticos.model.observer;

import com.ufes.dadosclimaticos.model.DadosClimaticos;

public interface IObserver {
   public void update(DadosClimaticos dadosCimaticos);
}
