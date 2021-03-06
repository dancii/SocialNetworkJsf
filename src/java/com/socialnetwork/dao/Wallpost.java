/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialnetwork.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dancii
 */
@Entity
@Table(name = "Wallpost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wallpost.findAll", query = "SELECT w FROM Wallpost w"),
    @NamedQuery(name = "Wallpost.findById", query = "SELECT w FROM Wallpost w WHERE w.id = :id"),
    @NamedQuery(name = "Wallpost.findByFromUsername", query = "SELECT w FROM Wallpost w WHERE w.fromUsername = :fromUsername"),
    @NamedQuery(name = "Wallpost.findByToUsername", query = "SELECT w FROM Wallpost w WHERE w.toUsername = :toUsername"),
    @NamedQuery(name = "Wallpost.findByDate", query = "SELECT w FROM Wallpost w WHERE w.date = :date")})
public class Wallpost implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "fromUsername")
    private String fromUsername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "toUsername")
    private String toUsername;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Wallpost() {
    }

    public Wallpost(Integer id) {
        this.id = id;
    }

    public Wallpost(Integer id, String message, String fromUsername, String toUsername, Date date) {
        this.id = id;
        this.message = message;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wallpost)) {
            return false;
        }
        Wallpost other = (Wallpost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.socialnetwork.dao.Wallpost[ id=" + id + " ]";
    }
    
}
