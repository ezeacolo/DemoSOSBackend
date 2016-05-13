package com.demosos.exception;

/**
 * Excepcion generic que maneja los mensajes recibidos como parametros.
 *
 * @author DemoSOS
 */
@SuppressWarnings("serial")
public class GenericException extends Exception {

    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(Throwable cause) {
        super(cause);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

}