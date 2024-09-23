package br.com.selectgearmotors.bff.commons.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenUtil {

    // Método estático para remover o prefixo "Bearer " do token
    public static String removeBearerPrefix(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.replaceFirst("Bearer ", "");
        }
        // Retorna o token original caso não tenha o prefixo "Bearer "
        return token;
    }
}
