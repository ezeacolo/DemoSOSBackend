/*
 *  FileName - UsuarioSocial.java
 *
 *
 *  $Author: ezequiel $
 *  $Date: 11/3/15 11:24 PM $
 */

package com.demosos.domain;

import com.demosos.util.Constants;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Esta clase representa un usuario logueado por alguna red
 * social
 *
 * @author DemoSOS
 */
@Configurable
@Entity
@Table(name = UsuarioSocial.TABLE_NAME)
public class UsuarioSocial implements Serializable {

    public static final String TABLE_NAME = "Usuario_Social";
    public static final String TABLE_NAME_HQL = "UsuarioSocial";
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario_social")
    private Long id;
    @NotNull
    @Size(max = 50)
    // String que retornan los servicios de google, face, etc que
    // representa el ID UNICO que permite identificar el usuario
    @Column(name = "social_id")
    private String socialId;
    @NotNull
    // 1:facebook
    // 2:twitter
    // 3:google+
    @ManyToOne(targetEntity = RedSocial.class)
    @JoinColumn(name = "id_red_social")
    private RedSocial redSocial;
    //@NotNull
    @Size(max = 200)
    // Token tmp retornado por los services que implementan el protocolo oauth
    // Es un token que sirve para que luego de que un usuario te autorizo a que
    // accedas a sus datos, a poder hacer queries a API
    @Column(name = "social_token")
    private String socialToken;
    @NotNull
    @OneToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    // constructores
    public UsuarioSocial() {
    }

    // instancia de em
    public static final EntityManager entityManager() {
        EntityManager em = new UsuarioSocial().entityManager;
        if (em == null)
            throw new IllegalStateException(
                    Constants.ENTITY_MANAGER_HAS_NOT_BEEN_INJECTED);
        return em;
    }

    // metodos get
    public static UsuarioSocial findUsuario(Long id) {
        if (id == null) return null;
        return entityManager().find(UsuarioSocial.class, id);
    }

    public static boolean existUsuario(String socialId, Long redSocial) {
        StringBuilder sqlExistUsuario = new StringBuilder();
        sqlExistUsuario.append("SELECT COUNT(*) FROM ").append(TABLE_NAME_HQL);

        sqlExistUsuario.append(" WHERE ").append("socialId = '").append(socialId).append("' AND redSocial.id = ").append(redSocial);
        return entityManager().createQuery(sqlExistUsuario.toString(), Long.class).getSingleResult() == 1;
    }

    // getter /setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public RedSocial getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    public String getSocialToken() {
        return socialToken;
    }

    public void setSocialToken(String socialToken) {
        this.socialToken = socialToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // metodos save/update/delete
    @Transactional
    public void persist() {
        //primero guardamos el usuario
        this.getUsuario().persist();

        //luego el usuario social
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UsuarioSocial usuario = UsuarioSocial.findUsuario(this.id);
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
    public UsuarioSocial merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UsuarioSocial merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }


    public String toString() {
        return this.toString();
    }

}
