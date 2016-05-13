package com.demosos.util;

import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * GenericRequest
 *
 * @author DemoSOS
 */
@SuppressWarnings("serial")
public class GenericRequest implements Serializable {
    final static Logger log = Logger.getLogger(GenericRequest.class);

    private String passwordActual;
    private String passwordNuevo;

    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passwordActual) {
        this.passwordActual = passwordActual;
    }

    public String getPasswordNuevo() {
        return passwordNuevo;
    }

    public void setPasswordNuevo(String passwordNuevo) {
        this.passwordNuevo = passwordNuevo;
    }

}