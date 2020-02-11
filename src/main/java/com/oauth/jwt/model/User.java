package com.oauth.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    private String name;

    @NaturalId
    @NotNull
    @Size(max = 40)
    @Email
    @JsonIgnore
    private String email;

    @NotNull
    @Size(max = 100)
    @JsonIgnore
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date update;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    private String jwt;

    private Boolean isActivo;



    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_phones", joinColumns = @JoinColumn(name = "user_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "number")),
            @AttributeOverride(name = "citycode", column = @Column(name = "citycode")),
            @AttributeOverride(name = "countrycode", column = @Column(name = "countrycode"))

    })
    private Set<ContactInfo> phoness = new HashSet<>();

    public User() {
    }

    public User(@NotNull @Size(max = 40) String name, @NotNull @Size(max = 40) @Email String email, @NotNull @Size(max = 100) String password, Set<ContactInfo> phoness, Date created, Date update, Date lastLogin,Boolean isActivo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoness = phoness;
        this.created = created;
        this.update = update;
        this.lastLogin = lastLogin;
        this.isActivo = isActivo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdate() {
        return update;
    }

    public User setUpdate(Date update) {
        this.update = update;
        return this;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<ContactInfo> getPhoness() {
        return phoness;
    }

    public void setPhoness(Set<ContactInfo> phoness) {
        this.phoness = phoness;
    }

    public String getJwt() {
        return jwt;
    }

    public User setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public Boolean getActivo() {
        return isActivo;
    }

    public void setActivo(Boolean activo) {
        isActivo = activo;
    }
}
