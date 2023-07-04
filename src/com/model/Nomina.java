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
@Table(name = "NOMINA")
@NamedQueries({
    @NamedQuery(name = "Nomina.findAll", query = "SELECT n FROM Nomina n"),
    @NamedQuery(name = "Nomina.findById", query = "SELECT n FROM Nomina n WHERE n.id = :id"),
    @NamedQuery(name = "Nomina.findByFechaInicio", query = "SELECT n FROM Nomina n WHERE n.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Nomina.findByFechaBaja", query = "SELECT n FROM Nomina n WHERE n.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Nomina.findByPrestamos", query = "SELECT n FROM Nomina n WHERE n.prestamos = :prestamos"),
    @NamedQuery(name = "Nomina.findByDiasLaborados", query = "SELECT n FROM Nomina n WHERE n.diasLaborados = :diasLaborados"),
    @NamedQuery(name = "Nomina.findBySueldoDiario", query = "SELECT n FROM Nomina n WHERE n.sueldoDiario = :sueldoDiario"),
    @NamedQuery(name = "Nomina.findByDescuentos", query = "SELECT n FROM Nomina n WHERE n.descuentos = :descuentos"),
    @NamedQuery(name = "Nomina.findBySueldoNeto", query = "SELECT n FROM Nomina n WHERE n.sueldoNeto = :sueldoNeto"),
    @NamedQuery(name = "Nomina.findByObservaciones", query = "SELECT n FROM Nomina n WHERE n.observaciones = :observaciones"),
    @NamedQuery(name = "Nomina.findByPeriodo", query = "SELECT n FROM Nomina n WHERE n.periodo = :periodo")})
public class Nomina implements Serializable {

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
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prestamos")
    private Float prestamos;
    @Basic(optional = false)
    @Column(name = "dias_laborados")
    private int diasLaborados;
    @Basic(optional = false)
    @Column(name = "sueldo_diario")
    private float sueldoDiario;
    @Column(name = "descuentos")
    private Float descuentos;
    @Basic(optional = false)
    @Column(name = "sueldo_neto")
    private float sueldoNeto;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "periodo")
    private String periodo;

    public Nomina() {
    }

    public Nomina(Integer id) {
        this.id = id;
    }

    public Nomina(Integer id, Date fechaInicio, Date fechaBaja, int diasLaborados, float sueldoDiario, float sueldoNeto, String periodo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaBaja = fechaBaja;
        this.diasLaborados = diasLaborados;
        this.sueldoDiario = sueldoDiario;
        this.sueldoNeto = sueldoNeto;
        this.periodo = periodo;
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

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Float getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Float prestamos) {
        this.prestamos = prestamos;
    }

    public int getDiasLaborados() {
        return diasLaborados;
    }

    public void setDiasLaborados(int diasLaborados) {
        this.diasLaborados = diasLaborados;
    }

    public float getSueldoDiario() {
        return sueldoDiario;
    }

    public void setSueldoDiario(float sueldoDiario) {
        this.sueldoDiario = sueldoDiario;
    }

    public Float getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(Float descuentos) {
        this.descuentos = descuentos;
    }

    public float getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(float sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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
        if (!(object instanceof Nomina)) {
            return false;
        }
        Nomina other = (Nomina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Nomina[ id=" + id + " ]";
    }
    
}
