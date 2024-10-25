package br.com.pucminas.healthcore.application.reuniao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import static br.com.pucminas.healthcore.domain.constant.I18n.URL_BASE;

@Service
@RequiredArgsConstructor
public class ReunicaoServiceImpl implements ReunicaoService {

    @Override
    public String geradorReuniao() {
        try{
            return URL_BASE.concat(geradorID(UUID.randomUUID().toString(),12));
        }catch(Exception err){
            throw new RuntimeException(err.getMessage());
        }

    }

    public static String geradorID(String input, int length) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        String base64Hash = Base64.getUrlEncoder().encodeToString(hashBytes);
        return base64Hash.substring(0, length);
    }
}
