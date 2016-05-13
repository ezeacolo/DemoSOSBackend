package com.demosos.controller;

import com.demosos.domain.Barrio;
import com.demosos.service.BarrioService;
import com.demosos.util.SecureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DemoSOS
 */
@Controller
public class BarrioController {

    @Autowired
    BarrioService barrioService;

    /**
     * Buscar los barrios de una pcia y localidad determinada
     *
     * @param provincia
     * @param localidad
     * @param tokenEncoded
     * @return
     */
    @RequestMapping(value = "/barrios", method = RequestMethod.GET)
    @ResponseBody
    public SecureResponse barrios(@RequestParam("provincia") Long provincia,
                                  @RequestParam("localidad") Long localidad,
                                  @RequestHeader("token") String tokenEncoded) {

        SecureResponse result = null;

        try {

            List<Barrio> listBarrios = barrioService.barrios(provincia, localidad, tokenEncoded);

            result = SecureResponse.success(listBarrios);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }
}