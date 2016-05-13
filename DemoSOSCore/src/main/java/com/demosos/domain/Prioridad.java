package com.demosos.domain;

import com.demosos.util.Constants;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase representa una prioridad de incidente o de orden
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = Prioridad.TABLE_NAME)
public class Prioridad implements Serializable {

    public static final String TABLE_NAME = "Prioridad";
    private static final long serialVersionUID = 1L;
    private static final String ALIAS = "pr";
    private static final String FROM_TABLE = " FROM " + TABLE_NAME + " " + ALIAS + " ";
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_prioridad")
    private Long id;
    @NotNull
    @Size(max = 40)
    private String descripcion;


    //constructores
    public Prioridad() {
    }

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new Prioridad().entityManager;
        if (em == null) throw new IllegalStateException(Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    //metodos get
    public static List<Prioridad> findAllPrioridades() {
        String coverage = " 1 = 1 ";

        StringBuilder sqlFindAllPrioridad = new StringBuilder();
        sqlFindAllPrioridad.append("SELECT ").append(ALIAS).append(FROM_TABLE);
        //aplicar coverage
        //sqlFindAllPrioridad.append(" WHERE ").append(coverage.replace(Constants.ALIAS_TABLE, ALIAS));

        return entityManager().createQuery(sqlFindAllPrioridad.toString(), Prioridad.class).getResultList();
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

    //revisar
    public String toString() {
        return this.toString();
    }

}
