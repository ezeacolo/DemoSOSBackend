package com.demosos.service;

import com.demosos.MD5.Encoder;
import com.demosos.domain.Usuario;
import com.demosos.exception.GenericException;
import com.demosos.util.Constants;
import com.demosos.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author DemoSOS
 */
@Service(value = "usuarioService")
public class UsuarioService {
    final static Logger log = Logger.getLogger(UsuarioService.class);
    @Autowired
    TokenValidatorService tokenValidatorService;
    private String idGeneratorUsername;

    private String generateUsername(String userameTentativo) {

        String result = userameTentativo;
        // El username tentativo para crear es username.password, pero, podria ocurrir que el mismo ya
        // exista en la base, en dicho caso, sera necesario generarlo con un identificador despues del username

        // Validar nombre de usuario correcto?
        boolean existAlreadyUser = Usuario.existUsername(userameTentativo);

        if (existAlreadyUser) {
            //re-generate username
            idGeneratorUsername = String.valueOf(Integer.parseInt(idGeneratorUsername) + 1);
            return generateUsername(userameTentativo + idGeneratorUsername);
        }

        return result;
    }

    private String generateRandomPassword() {

        return Encoder.encrypt(generateRandomNumber());
    }

    private String generateRandomToken() {

        return generateRandomNumber();
    }

    private String generateRandomNumber() {

        return UUID.randomUUID().toString();
    }

    public String cambiarPasswordUsuario(String token, String passwordActual,
                                         String passwordNuevo) throws GenericException {

        String resultado = "";

        if (tokenValidatorService.tokenIsValid(token)) {

            // Encoded password recibidos del cliente
            passwordActual = Encoder.encrypt(passwordActual);
            passwordNuevo = Encoder.encrypt(passwordNuevo);

            // revisar si el passwordActual es correcto y se corresponde con el
            // del usuario en la base de datos
            Usuario usuario = Usuario.findUsuarioByUsername(Util
                    .getUsernameUsuarioLogueado(token));
            String passwordBBDD = usuario.getPassword();

            if (!passwordActual.equals(passwordBBDD)) {
                resultado = Constants.PASSWORD_ACTUAL_INCORRECTO;

            } else {

                // el passwordActual es diferente al passwordNuevo?
                if (passwordActual.equals(passwordNuevo)) {
                    resultado = Constants.PASSWORD_ACTUAL_IGUAL_NUEVO;

                } else {

                    // cambiar el password
                    usuario.setPassword(passwordNuevo);
                    usuario.merge();

                    resultado = "Success";
                }
            }

        } else {
            log.error(Constants.SESSION_INVALIDA + token);
            throw new GenericException(Constants.SESSION_INVALIDA);
        }

        return resultado;
    }
}