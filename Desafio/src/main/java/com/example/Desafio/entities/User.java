package com.example.Desafio.entities;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Id of the User",name="id",required=true,value="test id")
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String  id;

    @ApiModelProperty(notes = "Name of the User",name="name",required=true,value="test name")
    private String name;
    @ApiModelProperty(notes = "Email of the User",name="email",required=true,value="test email")
    private String email;

    @ApiModelProperty(notes = "UniqueTypes of the User",name="uniqueTypes",required=false,value="test uniqueTypes")
    @OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<UniqueType> uniqueTypes;

    @ApiModelProperty(notes = "PublicKey of the User",name="publicKey",required=false,value="test publicKey")
    @Column(length = 4096)
    private String publicKey;

    public User(){ }

    public void setId(String value) {
        this.id = value;
    }
    public String getId() {
        return id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UniqueType> getUniqueTypes() {
        return uniqueTypes;
    }

    public void setUniqueTypes(List<UniqueType> uniqueTypes) {
        this.uniqueTypes = uniqueTypes;
    }

    @Column(name="public_key")
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
