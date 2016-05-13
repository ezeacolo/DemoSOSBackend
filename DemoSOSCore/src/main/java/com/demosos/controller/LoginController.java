package com.demosos.controller;

import com.demosos.secure.UsuarioSec;
import com.demosos.service.LoginService;
import com.demosos.util.LoginRequest;
import com.demosos.util.SecureResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlador de login de la aplicacion
 *
 * @author DemoSOS
 */
@Controller
public class LoginController {
    final static Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public SecureResponse login(@RequestHeader("token") String tokenEncoded,
                                HttpServletRequest request) {

        SecureResponse result = null;

        try {

            LoginRequest loginRequest = new LoginRequest(tokenEncoded);

            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            UsuarioSec usuarioSec = loginService.login(username, password,
                    tokenEncoded);

            result = SecureResponse.success(usuarioSec);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
            log.error(result);
        }

        return result;
    }
}