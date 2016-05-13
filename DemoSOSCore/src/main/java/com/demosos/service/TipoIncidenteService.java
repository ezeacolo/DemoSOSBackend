package com.demosos.service;

import com.demosos.domain.TipoIncidente;
import com.demosos.exception.GenericException;
import com.demosos.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DemoSOS
 */
@Service(value = "tipoIncidenteService")
public class TipoIncidenteService {
    final static Logger log = Logger.getLogger(TipoIncidenteService.class);

    @Autowired
    TokenValidatorService tokenValidatorService;

    public List<TipoIncidente> tiposIncidente(String token) throws GenericException {
        List<TipoIncidente> listTiposIncidentes = null;

        if (tokenValidatorService.tokenIsValid(token)) {

            try {

                listTiposIncidentes = TipoIncidente.findAllTiposIncidentes();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new GenericException(Constants.TIPOS_INCIDENTES_NO_ENCONTRADAS);
            }

        } else {
            log.error(Constants.SESSION_INVALIDA + token);
            throw new GenericException(Constants.SESSION_INVALIDA);
        }

        return listTiposIncidentes;
    }

}