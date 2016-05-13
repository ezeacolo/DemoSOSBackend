package com.demosos.domain;

import com.demosos.util.Constants;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Esta clase representa un usuario
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario implements Serializable {

    public static final String TABLE_NAME = "Usuario";
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long id;
    @NotNull
    @Column(unique = true)
    @Size(max = 50)
    private String username;
    @Size(max = 20)
    private String nombre;//deberia ser not null
    @Size(max = 30)
    private String apellido;//deberia ser not null
    @NotNull
    @Size(max = 100)
    private String password;
    //@NotNull
    @Column(columnDefinition = "boolean default true")
    private Boolean enabled;
    @Transient
    private String token;


    //constructores
    public Usuario() {
    }

    //instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new Usuario().entityManager;
        if (em == null) throw new IllegalStateException(Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    //metodos get
    public static Usuario findUsuario(Long id) {
        if (id == null) return null;
        return entityManager().find(Usuario.class, id);
    }

    public static Usuario findUsuarioByUsername(String username) {
        if (username == null || username.trim().length() == 0) {
            return null;
        }

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT u FROM ").append(TABLE_NAME).append(" u ");
        sbQuery.append(" WHERE u.username = :username ");
        sbQuery.append("   AND u.enabled = true");

        return entityManager().createQuery(sbQuery.toString(), Usuario.class).setParameter("username", username).getSingleResult();
    }

    public static boolean existUsername(String username) {

        StringBuilder sqlExistUsuario = new StringBuilder();
        sqlExistUsuario.append("SELECT COUNT(*) FROM ").append(TABLE_NAME);
        sqlExistUsuario.append(" WHERE username = '").append(username).append("'");

        return entityManager().createQuery(sqlExistUsuario.toString(), Long.class).getSingleResult() == 1;
    }

    //getter /setter
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
            Usuario usuario = Usuario.findUsuario(this.id);
            this.entityManager.remove(usuario);
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
    public Usuario merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Usuario merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }


    public String toString() {
        return this.toString();
    }


}
