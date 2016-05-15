package com.demosos.service;

import com.demosos.domain.Incidente;
import com.demosos.exception.GenericException;
import com.demosos.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author DemoSOS
 */
@Service(value = "incidenteService")
public class IncidenteService {
    final static Logger log = Logger.getLogger(IncidenteService.class);

    @Autowired
    TokenValidatorService tokenValidatorService;

    public Incidente save(Incidente incidenteToSave, String token) throws GenericException {
        Incidente result = null;

        if (tokenValidatorService.tokenIsValid(token)) {

            try {
                //set Fecha Alta
                incidenteToSave.setFechaAlta(new Date());
                incidenteToSave.persist(token);

            } catch (Exception e) {
                e.printStackTrace();
                throw new GenericException(Constants.ERROR_GUARDANDO_INCIDENTE + e.getMessage());
            }

            result = incidenteToSave;
        } else {
            log.error(Constants.SESSION_INVALIDA + token);
            throw new GenericException(Constants.SESSION_INVALIDA);
        }

        return result;
    }
}