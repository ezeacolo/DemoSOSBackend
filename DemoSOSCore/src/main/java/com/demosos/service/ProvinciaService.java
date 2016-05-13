package com.demosos.service;

import com.demosos.domain.Provincia;
import com.demosos.exception.GenericException;
import com.demosos.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DemoSOS
 */
@Service(value = "provinciaService")
public class ProvinciaService {
    final static Logger log = Logger.getLogger(ProvinciaService.class);

    @Autowired
    TokenValidatorService tokenValidatorService;

    public List<Provincia> provincia(String token) throws GenericException {
        List<Provincia> listPcias = null;

        if (token == null || tokenValidatorService.tokenIsValid(token)) {

            try {
                listPcias = Provincia.findAllProvincia();

            } catch (Exception ex) {
                log.error("listPcias exc " + ex.getMessage());
                ex.printStackTrace();
                throw new GenericException(Constants.PROVINCIAS_NO_ENCONTRADAS);
            }

        } else {
            log.error(Constants.SESSION_INVALIDA + token);
            throw new GenericException(Constants.SESSION_INVALIDA);
        }

        return listPcias;
    }

}