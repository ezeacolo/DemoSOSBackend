package com.demosos.controller;

import com.demosos.domain.Prioridad;
import com.demosos.service.PrioridadService;
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
public class PrioridadController {

    @Autowired
    PrioridadService prioridadService;

    /**
     * Buscar todas las prioridades
     *
     * @param tokenEncoded
     * @return
     */
    @RequestMapping(value = "/prioridades", method = RequestMethod.GET)
    @ResponseBody
    public SecureResponse prioridades(@RequestHeader("token") String tokenEncoded) {

        SecureResponse result = null;

        try {

            List<Prioridad> listPrioridades = prioridadService.prioridades(tokenEncoded);

            result = SecureResponse.success(listPrioridades);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }
}