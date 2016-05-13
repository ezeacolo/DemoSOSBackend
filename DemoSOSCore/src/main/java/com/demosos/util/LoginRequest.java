package com.demosos.util;

import com.demosos.exception.GenericException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * LoginRequest
 *
 * @author DemoSOS
 */
@SuppressWarnings("serial")
public class LoginRequest implements Serializable {
    final static Logger log = Logger.getLogger(LoginRequest.class);

    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequest() {
        this(null, null);
    }

    public LoginRequest(String tokenEncoded) throws GenericException {
        // decoding byte array into base64
        try {
            final byte[] decodedB = Base64.decodeBase64(tokenEncoded);
            final String decodedStr = new String(decodedB);

            this.setUsername(decodedStr.split(Constants.TOKEN_SEPARATOR)[0]);
            this.setPassword(decodedStr.split(Constants.TOKEN_SEPARATOR)[1]);
        } catch (Exception e) {
            throw new GenericException(Constants.TOKEN_INCORRECTO);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest [username=" + username + ", password=" + password
                + "]";
    }

}