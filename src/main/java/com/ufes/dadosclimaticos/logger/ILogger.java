package com.ufes.dadosclimaticos.logger;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import java.util.List;

public interface ILogger {

   public void logSalvar(DadosClimaticos dadosClimaticos) throws Exception;

   public List<DadosClimaticos> Logler() throws Exception;

}
