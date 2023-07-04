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
import javax.persistence.Lob;
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
@Table(name = "SERVICIO")
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
    @NamedQuery(name = "Servicio.findById", query = "SELECT s FROM Servicio s WHERE s.id = :id"),
    @NamedQuery(name = "Servicio.findByFecha", query = "SELECT s FROM Servicio s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Servicio.findByCantidad", query = "SELECT s FROM Servicio s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "Servicio.findByDescripcion", query = "SELECT s FROM Servicio s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Servicio.findByPrecio", query = "SELECT s FROM Servicio s WHERE s.precio = :precio"),
    @NamedQuery(name = "Servicio.findByImporte", query = "SELECT s FROM Servicio s WHERE s.importe = :importe"),
    @NamedQuery(name = "Servicio.findByMetodoPago", query = "SELECT s FROM Servicio s WHERE s.metodoPago = :metodoPago"),
    @NamedQuery(name = "Servicio.findByEmpresa", query = "SELECT s FROM Servicio s WHERE s.empresa = :empresa"),
    @NamedQuery(name = "Servicio.findByProximoServicio", query = "SELECT s FROM Servicio s WHERE s.proximoServicio = :proximoServicio"),
    @NamedQuery(name = "Servicio.findByVehiculoId", query = "SELECT s FROM Servicio s WHERE s.vehiculoId = :vehiculoId")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "importe")
    private float importe;
    @Basic(optional = false)
    @Column(name = "metodo_pago")
    private String metodoPago;
    @Basic(optional = false)
    @Column(name = "empresa")
    private String empresa;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "proximo_servicio")
    @Temporal(TemporalType.DATE)
    private Date proximoServicio;
    @Basic(optional = false)
    @Column(name = "vehiculo_id")
    private int vehiculoId;

    public Servicio() {
    }

    public Servicio(Integer id) {
        this.id = id;
    }

    public Servicio(Integer id, Date fecha, float cantidad, String descripcion, float precio, float importe, String metodoPago, String empresa, int vehiculoId) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.importe = importe;
        this.metodoPago = metodoPago;
        this.empresa = empresa;
        this.vehiculoId = vehiculoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getProximoServicio() {
        return proximoServicio;
    }

    public void setProximoServicio(Date proximoServicio) {
        this.proximoServicio = proximoServicio;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
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
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Servicio[ id=" + id + " ]";
    }
    
}
