package com.ufes.dadosclimaticos.logger;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import java.util.List;

public interface ILogger {

   public void salvar(DadosClimaticos dadosClimaticos) throws Exception;
   public void remover(DadosClimaticos dadosClimaticos) throws Exception;
   public List<DadosClimaticos> ler() throws Exception;
}
