package br.com.pucminas.healthcore.commons.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DataProvider {
    LocalDate obterDataAtutal();

    LocalDateTime obterDataHoraAtual();
}
