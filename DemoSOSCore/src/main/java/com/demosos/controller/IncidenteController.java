package com.demosos.controller;

import com.demosos.domain.Incidente;
import com.demosos.service.IncidenteService;
import com.demosos.util.SecureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author DemoSOS
 */
@Controller
public class IncidenteController {

    @Autowired
    IncidenteService incidenteService;

    @RequestMapping(value = "/incidentes", method = RequestMethod.POST)
    @ResponseBody
    public SecureResponse saveIncidente(final @RequestBody Incidente incidenteToSave,
                                        final @RequestHeader("token") String tokenEncoded) {

        SecureResponse result = null;

        try {

            Incidente incidente = null;

            //guardar incidente
            incidente = incidenteService.save(incidenteToSave, tokenEncoded);

            result = SecureResponse.success(incidente);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }
}