package br.com.pucminas.healthcore.application.cep.service;

import br.com.pucminas.healthcore.adapter.cep.model.Coordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CepServiceImpl implements CepService {

    private final RestTemplate restTemplate;

    private static final String URL_TEMPLATE = "https://nominatim.openstreetmap.org/search?format=json&postalcode=%s&country=BR";
    @Override
    public double calcularDistanciaEntreCeps(String cepMedico, String cepPaciente) {

        log.debug("Calculando distância entre CEPs: {} e {}", cepMedico, cepPaciente);

        Coordinates[] coordenadasMedico = restTemplate.getForObject(String.format(URL_TEMPLATE, cepMedico), Coordinates[].class);
        Coordinates[] coordenadasPaciente = restTemplate.getForObject(String.format(URL_TEMPLATE, cepPaciente), Coordinates[].class);

        if (coordenadasMedico == null || coordenadasMedico.length == 0 || coordenadasPaciente == null || coordenadasPaciente.length == 0) {
            throw new RuntimeException("Não foi possível obter as coordenadas para os CEPs fornecidos.");
        }

        Coordinates medicoCoords = coordenadasMedico[0];
        Coordinates pacienteCoords = coordenadasPaciente[0];

        log.debug("Coordenadas para o CEP do médico ({}): [{}]", cepMedico, medicoCoords);
        log.debug("Coordenadas para o CEP do paciente ({}): [{}]", cepPaciente, pacienteCoords);

        return calcularDistancia(medicoCoords.getLat(), medicoCoords.getLon(), pacienteCoords.getLat(), pacienteCoords.getLon());
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon1 - lon2);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return (int) Math.round(distance);
    }


}
