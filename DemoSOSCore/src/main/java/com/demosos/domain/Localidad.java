package com.demosos.domain;

import com.demosos.util.Constants;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Esta clase representa una localidad
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = Localidad.TABLE_NAME)
public class Localidad implements Serializable {

    public static final String TABLE_NAME = "Localidad";
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_localidad")
    private Long id;
    @NotNull
    @Size(max = 20)
    private String descripcion;
    @NotNull
    @ManyToOne(targetEntity = Provincia.class)
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;


    //constructores
    public Localidad() {
    }

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new Localidad().entityManager;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String toString() {
        return this.toString();
    }


}
