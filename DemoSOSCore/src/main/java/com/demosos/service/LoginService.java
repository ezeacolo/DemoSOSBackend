package com.demosos.service;

import com.demosos.MD5.Encoder;
import com.demosos.domain.Usuario;
import com.demosos.exception.GenericException;
import com.demosos.secure.EHCache;
import com.demosos.secure.UsuarioSec;
import com.demosos.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * LoginService
 *
 * @author DemoSOS
 */
@Service(value = "loginService")
public class LoginService {
    final static Logger log = Logger.getLogger(LoginService.class);

    /**
     * Loguea el usuario al sistema y retorna un token que identificara al
     * usuarios durante el periodo que dure la session dado por la cache.
     *
     * @param username un nombre de usuario.
     * @param password un password.
     * @return un token de autenticacion.
     */
    public UsuarioSec login(String username, String password,
                            String token) throws GenericException {

        Usuario usuario = null;
        UsuarioSec usuarioSec = null;

        password = Encoder.encrypt(password);
        // log.info("PASSWORD ENCRIPTADA: " + password);

        try {
            usuario = Usuario.findUsuarioByUsername(username);
        } catch (Exception e) {
            throw new GenericException(Constants.USUARIO_PASSWORD_INCORRECTO);
        }

        if (password.equals(usuario.getPassword())) {
            usuarioSec = new UsuarioSec();
            usuarioSec.setIdUsuario(usuario.getId());
            usuarioSec.setUsername(username);
            usuarioSec.setNombre(usuario.getNombre());
            usuarioSec.setApellido(usuario.getApellido());

            EHCache.saveUsuario(token, usuarioSec);

        } else {
            throw new GenericException(Constants.USUARIO_PASSWORD_INCORRECTO);
        }

        return usuarioSec;//permisosUsuario;
    }
}