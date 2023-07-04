/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author keatnis
 */
@Entity
@Table(name = "ASIGNACION_UNIDAD")
@NamedQueries({
    @NamedQuery(name = "AsignacionUnidad.findAll", query = "SELECT a FROM AsignacionUnidad a"),
    @NamedQuery(name = "AsignacionUnidad.findById", query = "SELECT a FROM AsignacionUnidad a WHERE a.id = :id"),
    @NamedQuery(name = "AsignacionUnidad.findByFechaInicio", query = "SELECT a FROM AsignacionUnidad a WHERE a.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "AsignacionUnidad.findByFechaFin", query = "SELECT a FROM AsignacionUnidad a WHERE a.fechaFin = :fechaFin"),
    @NamedQuery(name = "AsignacionUnidad.findByKmInicio", query = "SELECT a FROM AsignacionUnidad a WHERE a.kmInicio = :kmInicio"),
    @NamedQuery(name = "AsignacionUnidad.findByKmFinal", query = "SELECT a FROM AsignacionUnidad a WHERE a.kmFinal = :kmFinal"),
    @NamedQuery(name = "AsignacionUnidad.findByStatus", query = "SELECT a FROM AsignacionUnidad a WHERE a.status = :status")})
public class AsignacionUnidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "km_inicio")
    private float kmInicio;
    @Basic(optional = false)
    @Column(name = "km_final")
    private float kmFinal;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public AsignacionUnidad() {
    }

    public AsignacionUnidad(Integer id) {
        this.id = id;
    }

    public AsignacionUnidad(Integer id, Date fechaInicio, Date fechaFin, float kmInicio, float kmFinal, String status) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.kmInicio = kmInicio;
        this.kmFinal = kmFinal;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public float getKmInicio() {
        return kmInicio;
    }

    public void setKmInicio(float kmInicio) {
        this.kmInicio = kmInicio;
    }

    public float getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(float kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof AsignacionUnidad)) {
            return false;
        }
        AsignacionUnidad other = (AsignacionUnidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.AsignacionUnidad[ id=" + id + " ]";
    }
    
}
