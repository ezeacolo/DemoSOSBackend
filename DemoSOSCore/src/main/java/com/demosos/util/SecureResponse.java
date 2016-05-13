package com.demosos.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SecureResponse implements Serializable {

    public static final String OK = "ok";
    public static final String FAIL = "fail";
    public static final String CHECK_FAIL = "check_fail";

    public static final String HTTP_STATUS_CODE_202 = "202 OK";
    public static final String HTTP_STATUS_CODE_401 = "401 Unauthorized";

    private String status;
    private Object result;
    private String code;
    private String exception;
    private String exceptionMessage;

    public SecureResponse() {
        this.status = "";
        this.result = null;
        this.exception = "";
        this.exceptionMessage = "";
    }

    public static SecureResponse success(Object result) {
        SecureResponse response = new SecureResponse();
        response.setResult(result);
        response.setCode(SecureResponse.HTTP_STATUS_CODE_202);
        response.setStatus(SecureResponse.OK);

        return response;
    }

    public static SecureResponse fail(Exception e) {
        SecureResponse response = new SecureResponse();
        response.setStatus(SecureResponse.FAIL);

        if (e != null) {
            response.setException(e.getClass().getName());
            response.setExceptionMessage(e.getMessage());

            if (Constants.SESSION_INVALIDA.equals(e.getMessage())
                    || Constants.SESSION_SIN_PERMISO.equals(e.getMessage())) {
                response.setCode(SecureResponse.HTTP_STATUS_CODE_401);
            }
        }

        return response;
    }

    public static SecureResponse checkFail() {
        SecureResponse response = new SecureResponse();
        response.setStatus(SecureResponse.CHECK_FAIL);

        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}
