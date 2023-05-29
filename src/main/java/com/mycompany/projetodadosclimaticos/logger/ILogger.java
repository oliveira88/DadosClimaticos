package com.mycompany.projetodadosclimaticos.logger;

import com.mycompany.projetodadosclimaticos.model.DadosClimaticos;
import java.util.List;

public interface ILogger {

   public void logSalvar(DadosClimaticos dadosClimaticos) throws Exception;

   public List<DadosClimaticos> Logler() throws Exception;

}
