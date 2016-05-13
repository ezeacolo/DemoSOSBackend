package com.demosos.domain;

import com.demosos.json.JsonDateDeserializer;
import com.demosos.json.JsonDateSerializer;
import com.demosos.util.Constants;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Esta clase representa un incidente de trabajo
 *
 * @author DemoSOS
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Configurable
@Entity
@Table(name = Incidente.TABLE_NAME)
public class Incidente implements Serializable {

    public static final String TABLE_NAME = "Incidente";
    private static final long serialVersionUID = 1L;
    private static final String ALIAS = "inc";
    private static final String FROM_TABLE = " FROM " + TABLE_NAME + " " + ALIAS + " ";
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_incidente")
    private Long id;
    @NotNull
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @NotNull
    @Size(max = 500)
    private String observaciones;
    @NotNull
    @ManyToOne(targetEntity = Prioridad.class)
    @JoinColumn(name = "id_prioridad")
    private Prioridad prioridad;
    @NotNull
    @ManyToOne(targetEntity = TipoIncidente.class)
    @JoinColumn(name = "id_tipo_incidente")
    private TipoIncidente tipoIncidente;
    @NotNull
    private String direccion;
    @NotNull
    private String barrio;//Barrio
    @NotNull
    private String localidad;
    @NotNull
    private String provincia;
    @NotNull
    private String pais;
    @NotNull
    private String latitud;//Coordenadas?
    @NotNull
    private String longitud;//Coordenadas?
    //id tmp utilizado para el Adjunto de las imagenes subidas al server
    @Transient
    private Long idTmp;
    @Transient
    private Double distancia;
    //, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE
    @OneToMany(targetEntity = Adjunto.class, mappedBy = "incidente",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Adjunto> adjuntos;
    //campos de control
    @Column(name = "fecha_creacion")
    //@JsonSerialize(using=JsonDateSerializer.class)
    //@JsonIgnore
    private Date fechaCreacion;
    @Column(name = "fecha_actualizacion")
    @JsonSerialize(using = JsonDateSerializer.class)
    //@JsonIgnore
    private Date fechaActualizacion;
    @Column(name = "creador_por")
    //@JsonIgnore
    private String creadoPor;
    @Column(name = "actualizado_por")
    //@JsonIgnore
    private String actualizadoPor;

    //constructores
    public Incidente() {
    }

    /*public Incidente(final Long id, final Date fechaAlta, final String direccion, final String latitud, final String longitud,
                     final String localidad, final String provincia, final String barrio, final Prioridad prioridad,
                     final TipoIncidente tipoIncidente, final String observaciones, final String creadoPor) {
        this.id = id;
        this.fechaAlta = fechaAlta;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.localidad = localidad;
        this.provincia = provincia;
        this.barrio = barrio;
        this.prioridad = prioridad;
        this.tipoIncidente = tipoIncidente;
        this.observaciones = observaciones;
        this.creadoPor = creadoPor;
    }*/

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new Incidente().entityManager;
        if (em == null) {
            throw new IllegalStateException(Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        }
        return em;
    }

    //metodos get
    public static long countIncidentes(String coverage) {
        StringBuilder sqlCountInc = new StringBuilder();
        sqlCountInc.append("SELECT COUNT(").append(ALIAS).append(")").append(FROM_TABLE);
        //aplicar coverage
        sqlCountInc.append(" WHERE ").append(coverage.replace(Constants.ALIAS_TABLE, ALIAS));
        return entityManager().createQuery(sqlCountInc.toString(), Long.class).getSingleResult();
    }

    public static List<Incidente> findAllIncidentes(String coverage) {
        StringBuilder sqlFindAllInc = new StringBuilder();
        sqlFindAllInc.append("SELECT ").append(ALIAS).append(FROM_TABLE);
        //aplicar coverage
        sqlFindAllInc.append(" WHERE ").append(coverage.replace(Constants.ALIAS_TABLE, ALIAS));

        return entityManager().createQuery(sqlFindAllInc.toString(), Incidente.class).getResultList();
    }

    public static Incidente findIncidente(Long id) {
        Incidente incidente = null;
        if (id != null) {
            incidente = entityManager().find(Incidente.class, id);
        }
        return incidente;
    }

    public static Incidente findIncidenteById(Long id) {
        Incidente incidente = null;
        if (id != null) {
            StringBuilder sqlFindIncById = new StringBuilder();
            sqlFindIncById.append("SELECT ").append(ALIAS).append(FROM_TABLE).append(" WHERE inc.id = :id_incidente");
            incidente = entityManager().createQuery(sqlFindIncById.toString(), Incidente.class).setParameter("id_incidente", id).getSingleResult();
        }
        return incidente;
    }

    //getter /setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public TipoIncidente getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(TipoIncidente tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
        this.longitud = longitud;
    }

    public Set<Adjunto> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(Set<Adjunto> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getActualizadoPor() {
        return actualizadoPor;
    }

    public void setActualizadoPor(String actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

    public Long getIdTmp() {
        return idTmp;
    }

    public void setIdTmp(Long idTmp) {
        this.idTmp = idTmp;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    //metodos save/update/delete
    @Transactional
    public void persist(String token) {

        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            final Incidente incidente = Incidente.findIncidente(this.id);
            this.entityManager.remove(incidente);
        }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.flush();
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.clear();
    }

    @Transactional()
    public Incidente merge(String token) {

        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }


        final Incidente merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return this.toString();
    }
}