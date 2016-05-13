package com.demosos.service;

import com.demosos.MD5.Encoder;
import com.demosos.domain.Usuario;
import com.demosos.domain.UsuarioSocial;
import com.demosos.exception.GenericException;
import com.demosos.secure.EHCache;
import com.demosos.secure.UsuarioSec;
import com.demosos.util.Constants;
import com.demosos.util.Util;
import org.apache.commons.lang.WordUtils;
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

    public UsuarioSocial save(UsuarioSocial usuarioToSave)
            throws GenericException {

        UsuarioSocial result = null;

        try {

            idGeneratorUsername = "1";

            // Validar si este usuario ya no existe en la base
            // Esto es, se valida el SOCIAL_ID y SOCIAL_RED recibidos en la base
            boolean existUsuario = UsuarioSocial.existUsuario(usuarioToSave.getSocialId(), usuarioToSave.getRedSocial().getId());

            if (!existUsuario) {
                // Se crea un nuevo usuario cuyo username sera nombre.apellido y password aleatorio

                String nombre = usuarioToSave.getUsuario().getNombre().toLowerCase();
                String apellido = usuarioToSave.getUsuario().getApellido().toLowerCase();

                String username = generateUsername(nombre + "." + apellido);

                usuarioToSave.getUsuario().setNombre(WordUtils.capitalize(nombre)); //en capital
                usuarioToSave.getUsuario().setApellido(WordUtils.capitalize(apellido)); //en capital
                usuarioToSave.getUsuario().setUsername(username); //en minusculas
                usuarioToSave.getUsuario().setPassword(generateRandomPassword());
                usuarioToSave.getUsuario().setEnabled(false);

                usuarioToSave.persist();

            }
            // Caso contrario:
            // No se registra el usuario dado que ya existe en la base, se omite el paso de registracion de usuario


            // Luego, ya sea el camino de creacion de un nuevo user o no, hay que loguear el usuario de manera manual

            UsuarioSec usuarioSec = new UsuarioSec();
            usuarioSec.setIdUsuario(usuarioToSave.getUsuario().getId());
            usuarioSec.setUsername(usuarioToSave.getUsuario().getUsername());

            // Es necesario generar un token
            String token = generateRandomToken();
            usuarioToSave.getUsuario().setToken(token);
            EHCache.saveUsuario(token, usuarioSec);


        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException(Constants.ERROR_GUARDANDO_USUARIO + e.getMessage());
        }

        result = usuarioToSave;

        return result;
    }

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