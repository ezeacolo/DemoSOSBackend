package com.demosos.controller;

import com.demosos.domain.Provincia;
import com.demosos.service.ProvinciaService;
import com.demosos.util.SecureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author DemoSOS
 */
@Controller
public class ProvinciaController {

    @Autowired
    ProvinciaService provinciaService;

    /**
     * Buscar provincias para el usuario logueado
     *
     * @param tokenEncoded
     * @return
     */
    @RequestMapping(value = "/provincias", method = RequestMethod.GET)
    @ResponseBody
    public SecureResponse provincia(@RequestHeader(required = false, value = "token") String tokenEncoded) {

        SecureResponse result = null;

        try {

            List<Provincia> listPcias = provinciaService.provincia(tokenEncoded);

            result = SecureResponse.success(listPcias);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }
}