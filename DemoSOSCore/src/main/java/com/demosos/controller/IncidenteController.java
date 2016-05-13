package com.demosos.controller;

import com.demosos.domain.Incidente;
import com.demosos.service.IncidenteService;
import com.demosos.util.SecureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            //guardar adjuntos
            //mover ajuntos de tmp a ...
            //incidente.setId(null);
            incidente = incidenteService.save(incidenteToSave, tokenEncoded);

            result = SecureResponse.success(incidente);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }

    @RequestMapping(value = "/incidentes/{id}/fotos", method = RequestMethod.POST)
    @ResponseBody
    public SecureResponse uploadIncidentesFotos(final @PathVariable("id") Long id,
                                                final @RequestHeader("token") String tokenEncoded,
                                                final @RequestParam("file") MultipartFile file) {
        SecureResponse result = null;

        try {

            final String rdoUpload = incidenteService.uploadIncidentesFotos(id, tokenEncoded, file);

            result = SecureResponse.success(rdoUpload);

        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }
}