package com.demosos.controller;

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