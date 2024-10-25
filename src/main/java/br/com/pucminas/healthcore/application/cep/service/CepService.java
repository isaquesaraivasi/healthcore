package br.com.pucminas.healthcore.application.cep.service;
@FunctionalInterface
public interface CepService {
    double calcularDistanciaEntreCeps(String cepMedico, String cepPaciente);
}
