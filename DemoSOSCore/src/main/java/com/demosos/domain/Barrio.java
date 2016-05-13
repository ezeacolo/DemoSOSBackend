package com.demosos.domain;

import com.demosos.util.Constants;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase representa un barrio
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = Barrio.TABLE_NAME)
public class Barrio implements Serializable {

    public static final String TABLE_NAME = "Barrio";
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_barrio")
    private Long id;
    @NotNull
    @Size(max = 20)
    private String descripcion;
    @NotNull
    @ManyToOne(targetEntity = Localidad.class)
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;


    //constructores
    public Barrio() {
    }

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new Barrio().entityManager;
        if (em == null) throw new IllegalStateException(Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    //metodos get
    public static List<Barrio> findAllBarrios(Long provincia, Long localidad) {
        String sqlFindAllBarrios = "SELECT o FROM " + TABLE_NAME + " o WHERE o.localidad.provincia.id = :provincia AND o.localidad.id = :localidad";
        return entityManager().createQuery(sqlFindAllBarrios, Barrio.class).
                setParameter("provincia", provincia).setParameter("localidad", localidad).getResultList();
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

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String toString() {
        return this.toString();
    }


}
