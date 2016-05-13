/*
 *  FileName - RedSocial.java
 *
 *
 *  $Author: ezequiel $
 *  $Date: 11/3/15 11:25 PM $
 */

package com.demosos.domain;

import com.demosos.util.Constants;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Esta clase representa los diferentes tipos de redes sociales
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = RedSocial.TABLE_NAME)
public class RedSocial implements Serializable {

    public static final String TABLE_NAME = "Red_Social";
    public static final String TABLE_NAME_HQL = "RedSocial";
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_red_social")
    private Long id;
    @Size(max = 20)
    private String descripcion;

    // constructores
    public RedSocial() {
    }

    // instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new RedSocial().entityManager;
        if (em == null)
            throw new IllegalStateException(
                    Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    // getter /setter
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

    // metodos get

    public String toString() {
        return this.toString();
    }

}
