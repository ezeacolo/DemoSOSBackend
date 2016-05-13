package com.demosos.domain;

import com.demosos.util.Constants;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase representa una provincia
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = Provincia.TABLE_NAME)
public class Provincia implements Serializable {

    public static final String TABLE_NAME = "Provincia";
    private static final long serialVersionUID = 1L;
    private static final String ALIAS = "pr";
    private static final String FROM_TABLE = " FROM " + TABLE_NAME + " " + ALIAS + " ";
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_provincia")
    private Long id;
    @NotNull
    @Size(max = 20)
    private String descripcion;


    //constructores
    public Provincia() {
    }

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new Provincia().entityManager;
        if (em == null) throw new IllegalStateException(Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    //metodos get
    public static List<Provincia> findAllProvincia() {
        StringBuilder sqlFindAllPcias = new StringBuilder();
        sqlFindAllPcias.append("SELECT ").append(ALIAS).append(FROM_TABLE);

        return entityManager().createQuery(sqlFindAllPcias.toString(), Provincia.class).getResultList();
    }

    public static Provincia findProvincia(Long id) {
        if (id == null) return null;
        return entityManager().find(Provincia.class, id);
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

    public String toString() {
        return this.toString();
    }


}
