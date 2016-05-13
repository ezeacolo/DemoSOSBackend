package com.demosos.controller;

import com.demosos.domain.UsuarioSocial;
import com.demosos.service.UsuarioService;
import com.demosos.util.GenericRequest;
import com.demosos.util.SecureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author DemoSOS
 */
@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Por ahora crea usuarios de la red social
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    @ResponseBody
    public SecureResponse saveUsuario(
            final @RequestBody UsuarioSocial usuarioToSave) {

        SecureResponse result = null;

        try {

            UsuarioSocial usuario = usuarioService.save(usuarioToSave);

            // Estamos retornando un objeto usuario social, ya sea en caso que se creo uno nuevo
            // o en el caso que el mismo ya exista, entonces con los datos de login

            result = SecureResponse.success(usuario);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }

    @RequestMapping(value = "/usuarios/cambiarpassword", method = RequestMethod.POST)
    @ResponseBody
    public SecureResponse cambiarPasswordUsuario(
            @RequestHeader("token") String tokenEncoded,
            @RequestBody GenericRequest generic) {

        SecureResponse result = null;

        try {

            String passwordActual = generic.getPasswordActual();
            String passwordNuevo = generic.getPasswordNuevo();

            String resultado = usuarioService.cambiarPasswordUsuario(
                    tokenEncoded, passwordActual, passwordNuevo);

            result = SecureResponse.success(resultado);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }

}