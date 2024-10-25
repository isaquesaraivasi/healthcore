package br.com.pucminas.healthcore.commons.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthUtilImpl implements AuthUtil {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encriptarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    @Override
    public boolean validarSenha(String senha, String senhaCriptografada) {
        return passwordEncoder.matches(senha, senhaCriptografada);
    }
}
