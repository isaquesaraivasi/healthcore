package br.com.pucminas.healthcore.commons.utils;

public interface AuthUtil {
    String encriptarSenha(String senha);
    boolean validarSenha(String senha, String senhaCriptografada);

}
