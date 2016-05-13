package com.demosos.util;

import com.demosos.secure.EHCache;
import com.demosos.secure.UsuarioSec;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {

    final static Logger log = Logger.getLogger(Util.class);

    public static SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
    public static SimpleDateFormat formatterWithHour = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_HOUR);

    /**
     * get username de usuario logueado
     *
     * @param token
     * @return
     */
    public static String getUsernameUsuarioLogueado(String token) {
        final UsuarioSec usuario = (UsuarioSec) EHCache.loadUsuario(token);
        final String username = usuario.getUsername();

        return username;
    }

    /**
     * get id de usuario logueado
     *
     * @param token
     * @return
     */
    public static Long getIdUsuarioLogueado(String token) {
        final UsuarioSec usuario = (UsuarioSec) EHCache.loadUsuario(token);
        final Long usuarioId = usuario.getIdUsuario();

        return usuarioId;
    }

    /**
     * para los endDate es necesario que sumemos un dia, sino no consideramos en las busquedas
     * aquellos incidentes/ordenes que tiene como fecha de filtro la que viene pasada de parametro
     * formatter.parse crea un Date con hora 00:00:00, esto es el comienzo del dia
     *
     * @param dateStrToValidate
     * @param isEndDate
     * @return
     */
    public static Date parseValidateDate(String dateStrToValidate, Boolean isEndDate) {
        Date dateValidated = null;

        try {
            formatter.parse(dateStrToValidate);
            dateValidated = formatter.parse(dateStrToValidate);
            if (isEndDate) {
                dateValidated = sumarFechasDias(dateValidated, 1);
            }
        } catch (Exception e) {
            //dateStrToValidate = null;
            //e.printStackTrace();
            //System.out.println("parseValidateDate incorrecto");
            log.error("parseValidateDate incorrecto");
        }

        return dateValidated;
    }

    /**
     * @param dateToFormatToString
     * @return
     */
    public static String formatValidateDate(Date dateToFormatToString) {
        String fechaValidatedConverted = "";

        fechaValidatedConverted = formatterWithHour.format(dateToFormatToString);

        return fechaValidatedConverted;
    }

    /**
     * @param dateString
     * @return
     */
    public static Date parseDate(String dateString) {
        Date parseDate = null;
        try {
            parseDate = Util.formatterWithHour.parse(dateString);
        } catch (ParseException e) {
            //throw new RuntimeException(e);
            //System.out.println("parseDate incorrecto");
            log.error("parseDate incorrecto");
        }

        return parseDate;
    }


    /**
     * Sumarle dias a una fecha determinada
     *
     * @param fch  La fecha para sumarle los dias
     * @param dias Numero de dias a agregar
     * @return La fecha agregando los dias
     */
    public static Date sumarFechasDias(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Redondear un nro double ccon la cantidad de decimales necesarios
     *
     * @param numero
     * @param decimales
     * @return
     */
    public static double redondear(double numero, int decimales) {
        return Math.round(numero * Math.pow(10, decimales)) / Math.pow(10, decimales);
    }

}
