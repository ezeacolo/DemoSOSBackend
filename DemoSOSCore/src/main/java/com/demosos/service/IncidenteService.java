package com.demosos.service;

import com.demosos.domain.Adjunto;
import com.demosos.domain.Incidente;
import com.demosos.exception.GenericException;
import com.demosos.util.Constants;
import com.demosos.util.ProxyProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author DemoSOS
 */
@Service(value = "incidenteService")
public class IncidenteService {
    final static Logger log = Logger.getLogger(IncidenteService.class);

    @Autowired
    TokenValidatorService tokenValidatorService;
    @Autowired
    AdjuntoService adjuntoService;

    public Incidente save(Incidente incidenteToSave, String token) throws GenericException {
        Incidente result = null;

        if (tokenValidatorService.tokenIsValid(token)) {

            try {
                //set Fecha Alta
                incidenteToSave.setFechaAlta(new Date());

                incidenteToSave.persist(token);

                /*Long idIncTmp;
                // Si tiene idTmp, se han subido fotos/adjuntos
                if ((idIncTmp = incidenteToSave.getIdTmp()) != null) {
                    adjuntoService.save(incidenteToSave, idIncTmp, token);
                }*/

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

    public String uploadIncidentesFotos(Long incidenteId, String token, MultipartFile file) throws GenericException {
        String result = "";

        FileOutputStream fos = null;

        if (tokenValidatorService.tokenIsValid(token)) {

            try {

                ProxyProperties properties = new ProxyProperties();

                //Obtener la ruta del server donde la imagen sera guardada
                String strPathUploadDest = properties.getPathUploadPhoto()
                        + File.separator + incidenteId;

                //Guardar la imagen en la ruta obtenida del fichero de propiedades proxyconf
                boolean fileIsCreated = new File(strPathUploadDest).mkdirs();
                log.debug("File created: " + fileIsCreated);

                fos = new FileOutputStream(new File(strPathUploadDest + File.separator + file.getOriginalFilename()));
                fos.write(file.getBytes());

                //Guardar datos de imagen en la tabla Adjunto
                Adjunto adjunto = new Adjunto();
                Incidente incidente = new Incidente();
                incidente.setId(incidenteId);
                adjunto.setIncidente(incidente);
                adjunto.setNombreAdjunto(file.getOriginalFilename());
                adjunto.setSize(file.getSize());
                adjunto.persist();
                //FALTAN CONTROLES Y REVISAR SI ES LA MANERA CORRECTA!?

            } catch (Exception e) {
                e.printStackTrace();
                throw new GenericException(Constants.ERROR_ADJUNTANDO_IMAGEN + e.getMessage());

            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException io) {
                        System.out.println(io.getMessage());
                    }
                }
            }

            result = "Sucess";//?

        } else {
            log.error(Constants.SESSION_INVALIDA + token);
            throw new GenericException(Constants.SESSION_INVALIDA);
        }

        return result;
    }
}