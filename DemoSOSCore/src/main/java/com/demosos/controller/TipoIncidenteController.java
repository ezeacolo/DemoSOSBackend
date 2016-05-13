package com.demosos.controller;

import com.demosos.domain.TipoIncidente;
import com.demosos.service.TipoIncidenteService;
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
public class TipoIncidenteController {

    @Autowired
    TipoIncidenteService tipoIncidenteService;

    /**
     * Buscar todos los tipos de incidentes
     *
     * @param tokenEncoded
     * @return
     */
    @RequestMapping(value = "/tiposIncidente", method = RequestMethod.GET)
    @ResponseBody
    public SecureResponse tiposIncidente(@RequestHeader("token") String tokenEncoded) {

        SecureResponse result = null;

        try {

            List<TipoIncidente> listTiposIncidentes = tipoIncidenteService.tiposIncidente(tokenEncoded);

            result = SecureResponse.success(listTiposIncidentes);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = SecureResponse.fail(ex);
        }

        return result;
    }
}