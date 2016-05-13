package com.demosos.secure;

import java.io.Serializable;

/**
 * Este objeto sera guardado en el contenedor de seguridad y representa un
 * usuario valido de la base de datos.
 *
 * @author DemoSOS
 */
public class UsuarioSec implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUsuario;
    private String username;
    private String nombre;
    private String apellido;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
