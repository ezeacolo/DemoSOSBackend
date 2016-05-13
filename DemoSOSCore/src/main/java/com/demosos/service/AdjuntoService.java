package com.demosos.service;

import com.demosos.domain.Adjunto;
import com.demosos.domain.Incidente;
import com.demosos.exception.GenericException;
import com.demosos.util.Constants;
import com.demosos.util.ProxyProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author DemoSOS
 */
@Service(value = "adjuntoService")
public class AdjuntoService {
    final static Logger log = Logger.getLogger(AdjuntoService.class);

    @Autowired
    TokenValidatorService tokenValidatorService;

    /**
     * save adjuntos del incidente
     *
     * @param incidente    (antes era incidenteId)
     * @param incidenteTmp
     * @param token
     * @return mensaje
     * @throws GenericException
     */
    public String save(Incidente incidente, Long incidenteTmp, String token) throws GenericException {
        String result = null;

        if (tokenValidatorService.tokenIsValid(token)) {

            try {

                ProxyProperties properties = new ProxyProperties();

                Adjunto adjunto = null;

                String strPathUploadOrig = properties.getPathUploadTmpPhoto() + incidenteTmp;
                String strPathUploadDest = properties.getPathUploadPhoto()
                        + File.separator + incidente.getId();

                // Obtener una referencia a los dir origen y destino
                File dirOrig = new File(strPathUploadOrig);
                File dirDest = new File(strPathUploadDest);

                // Recuperaremos los ficheros que componen el directorio origen
                String[] ficheros = dirOrig.list();

                // Recorrer el array para sacar sus datos
                if (ficheros == null) {
                    //System.out.println("No hay ficheros en el directorio especificado");
                } else {

                    for (String fichero : ficheros) {

                        //Guardar datos de imagen en la tabla Adjunto
                        adjunto = new Adjunto();
                        // Seteo el Incidente completo para evitar NullPointer en Adjunto.getUrl()
                        adjunto.setIncidente(incidente);
                        adjunto.setNombreAdjunto(fichero);
                        adjunto.setSize(1);// FIXME Size de archivo harcodeado
                        adjunto.persist();
                        // Agrego .add porque sino si hago un Incidente.merge después de esto
                        // elimina el adjunto que no está en "incidente"
                        incidente.getAdjuntos().add(adjunto);
                    }
                }

                copyDirectory(dirOrig, dirDest);

            } catch (Exception e) {
                e.printStackTrace();
                throw new GenericException(
                        Constants.ERROR_GUARDANDO_ADJUNTOS_INCIDENTE
                                + e.getMessage());
            }

            result = "OK Incidente/Adjunto Save";
        } else {
            log.error(Constants.SESSION_INVALIDA + token);
            throw new GenericException(Constants.SESSION_INVALIDA);
        }

        return result;
    }

    /**
     * Copia un directorio con todo y su contendido.
     * <blockquote>Atención: elimina srcDir y su contenido</blockquote>
     *
     * @param srcDir
     * @param dstDir
     * @throws IOException
     */
    private void copyDirectory(File srcDir, File dstDir) throws IOException {
        if (srcDir.isDirectory()) {
            if (!dstDir.exists()) {
                //dstDir.mkdir();
                dstDir.mkdirs();
            }

            String[] children = srcDir.list();
            for (String aChildren : children) {
                copyDirectory(new File(srcDir, aChildren),
                        new File(dstDir, aChildren));
            }
        } else {
            copy(srcDir, dstDir);
        }

        srcDir.delete();
    }

    /**
     * Copia un solo archivo
     *
     * @param src
     * @param dst
     * @throws IOException
     */
    private void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);


        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}