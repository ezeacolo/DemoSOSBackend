package com.demosos.domain;

import com.demosos.util.Constants;
import com.demosos.util.ProxyProperties;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase representa una imagen de incidente (adjunto)
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = Adjunto.TABLE_NAME)
public class Adjunto implements Serializable {

    public static final String TABLE_NAME = "Adjunto";
    private static final long serialVersionUID = 1L;
    private static final ProxyProperties properties = new ProxyProperties();
    private static final String ALIAS = "adj";
    private static final String FROM_TABLE = " FROM " + TABLE_NAME + " " + ALIAS + " ";
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_adjunto")
    private Long id;
    @NotNull
    @Size(max = 250)
    @Column(name = "nombre_adjunto")
    private String nombreAdjunto;
    @NotNull
    private long size;
    @Transient
    private String url;
    @ManyToOne(targetEntity = Incidente.class)
    @JoinColumn(name = "id_incidente")
    @JsonIgnore
    private Incidente incidente;


    //constructores
    public Adjunto() {
    }

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new Adjunto().entityManager;
        if (em == null) throw new IllegalStateException(Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    public static Adjunto findAdjunto(Long id) {
        if (id == null) return null;
        return entityManager().find(Adjunto.class, id);
    }

    public static List<Adjunto> findAdjuntosxIncidenteId(Long incidenteId) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT adj ").append(FROM_TABLE);
        sql.append(" WHERE 1 = 1 ");
        sql.append("   AND adj.incidente.id = :id");

        Query query = entityManager().createQuery(sql.toString());

        query.setParameter("id", incidenteId);

        List<Adjunto> adjuntos = query.getResultList();

        return adjuntos;
    }

    //getter /setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    public String getUrl() {
        url = properties.getUrlDownloadPhoto() + Constants.TOKEN_SEPARATOR_HTML + "PROV" + Constants.TOKEN_SEPARATOR_HTML + "MUN";
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //metodos save/update/delete
    @Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Adjunto adjunto = Adjunto.findAdjunto(this.id);
            this.entityManager.remove(adjunto);
        }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

    @Transactional
    public Adjunto merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Adjunto merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return this.toString();
    }


}
