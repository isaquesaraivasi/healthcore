package br.com.pucminas.healthcore.commons.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataProviderImpl implements DataProvider {
    @Override
    public LocalDate obterDataAtutal() {
        return LocalDate.now();
    }
    @Override
    public LocalDateTime obterDataHoraAtual() {
        return LocalDateTime.now();
    }

}

