package com.demosos.domain;

import com.demosos.util.Constants;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase representa un tipo de incidente
 *
 * @author DemoSOS
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Configurable
@Entity
@Table(name = TipoIncidente.TABLE_NAME)
public class TipoIncidente implements Serializable {

    public static final String TABLE_NAME = "Tipo_Incidente";
    public static final String TABLE_NAME_HQL = "TipoIncidente";
    private static final long serialVersionUID = 1L;
    private static final String ALIAS = "tipo";
    private static final String FROM_TABLE = " FROM " + TABLE_NAME_HQL + " " + ALIAS + " ";

    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipo_incidente")
    private Long id;
    @Size(max = 40)
    private String descripcion;


    //constructores
    public TipoIncidente() {
    }

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new TipoIncidente().entityManager;
        if (em == null) throw new IllegalStateException(Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    //metodos get
    public static List<TipoIncidente> findAllTiposIncidentes() {

        StringBuilder sqlFindAllTipoInc = new StringBuilder();
        sqlFindAllTipoInc.append("SELECT DISTINCT ").append(ALIAS).append(FROM_TABLE);

        return entityManager().createQuery(sqlFindAllTipoInc.toString(), TipoIncidente.class).getResultList();
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
