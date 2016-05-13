package com.demosos.domain;

import com.demosos.util.Constants;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Esta clase representa un domicilio
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = Domicilio.TABLE_NAME)
public class Domicilio implements Serializable {

    public static final String TABLE_NAME = "Domicilio";
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_domicilio")
    private Long id;
    @NotNull
    @Size(max = 20)
    private String calle;
    @NotNull
    @Size(max = 6)
    private String numero;
    @NotNull
    private String latitud;
    @NotNull
    private String longitud;
    @NotNull
    @ManyToOne(targetEntity = Barrio.class)
    @JoinColumn(name = "id_barrio")
    private Barrio barrio;


    //constructores
    public Domicilio() {
    }

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new Domicilio().entityManager;
        if (em == null) throw new IllegalStateException(Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    //getter /setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = latitud;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String toString() {
        return this.toString();
    }


}
