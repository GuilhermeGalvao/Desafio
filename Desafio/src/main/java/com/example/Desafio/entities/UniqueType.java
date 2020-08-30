package com.example.Desafio.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="unique_type")
public class UniqueType implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Id of the UniqueType",name="id",required=true,value="test id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Number n of the UniqueType",name="n",required=true,value="test n")
    private String n;
    @ApiModelProperty(notes = "Number k of the UniqueType",name="k",required=true,value="test k")
    private Integer k;
    @ApiModelProperty(notes = "Result of uniqueType function of n repeated k times ",name="result",required=true,value="test result")
    private Integer result;

    @ApiModelProperty(notes = "Related user who requested the uniqueType ",name="user",required=true,value="test user")
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = true)
    private User user;

    public UniqueType(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="n")
    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    @Column(name="k")
    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    @Column(name="result")
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
