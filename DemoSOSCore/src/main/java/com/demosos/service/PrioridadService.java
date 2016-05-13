package com.demosos.service;

import com.demosos.domain.Prioridad;
import com.demosos.exception.GenericException;
import com.demosos.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DemoSOS
 */
@Service(value = "prioridadService")
public class PrioridadService {
    final static Logger log = Logger.getLogger(PrioridadService.class);

    @Autowired
    TokenValidatorService tokenValidatorService;

    public List<Prioridad> prioridades(String token) throws GenericException {
        List<Prioridad> listPrioridades = null;

        if (tokenValidatorService.tokenIsValid(token)) {

            try {

                listPrioridades = Prioridad.findAllPrioridades();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new GenericException(Constants.PRIORIDADES_NO_ENCONTRADAS);
            }

        } else {
            log.error(Constants.SESSION_INVALIDA + token);
            throw new GenericException(Constants.SESSION_INVALIDA);
        }

        return listPrioridades;
    }

}