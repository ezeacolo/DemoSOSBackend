package com.demosos.service;

import com.demosos.domain.Barrio;
import com.demosos.exception.GenericException;
import com.demosos.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DemoSOS
 */
@Service(value = "barrioService")
public class BarrioService {
    final static Logger log = Logger.getLogger(BarrioService.class);

    @Autowired
    TokenValidatorService tokenValidatorService;

    public List<Barrio> barrios(Long provincia, Long localidad, String token) throws GenericException {
        List<Barrio> listBarrios = null;

        if (tokenValidatorService.tokenIsValid(token)) {
            try {
                listBarrios = Barrio.findAllBarrios(provincia, localidad);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new GenericException(Constants.BARRIOS_NO_ENCONTRADOS);
            }

        } else {
            log.error(Constants.SESSION_INVALIDA + token);
            throw new GenericException(Constants.SESSION_INVALIDA);
        }

        return listBarrios;
    }

}