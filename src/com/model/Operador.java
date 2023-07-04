/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author keatnis
 */
@Entity
@Table(name = "OPERADOR")
@NamedQueries({
    @NamedQuery(name = "Operador.findAll", query = "SELECT o FROM Operador o"),
    @NamedQuery(name = "Operador.findById", query = "SELECT o FROM Operador o WHERE o.id = :id"),
    @NamedQuery(name = "Operador.findByNombre", query = "SELECT o FROM Operador o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Operador.findByApePaterno", query = "SELECT o FROM Operador o WHERE o.apePaterno = :apePaterno"),
    @NamedQuery(name = "Operador.findByApeMaterno", query = "SELECT o FROM Operador o WHERE o.apeMaterno = :apeMaterno"),
    @NamedQuery(name = "Operador.findByCalle", query = "SELECT o FROM Operador o WHERE o.calle = :calle"),
    @NamedQuery(name = "Operador.findByNum", query = "SELECT o FROM Operador o WHERE o.num = :num"),
    @NamedQuery(name = "Operador.findByColonia", query = "SELECT o FROM Operador o WHERE o.colonia = :colonia"),
    @NamedQuery(name = "Operador.findByCiudad", query = "SELECT o FROM Operador o WHERE o.ciudad = :ciudad"),
    @NamedQuery(name = "Operador.findByTelefono", query = "SELECT o FROM Operador o WHERE o.telefono = :telefono"),
    @NamedQuery(name = "Operador.findByCelular", query = "SELECT o FROM Operador o WHERE o.celular = :celular"),
    @NamedQuery(name = "Operador.findByContactoId", query = "SELECT o FROM Operador o WHERE o.contactoId = :contactoId")})
public class Operador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ape_paterno")
    private String apePaterno;
    @Basic(optional = false)
    @Column(name = "ape_materno")
    private String apeMaterno;
    @Basic(optional = false)
    @Column(name = "calle")
    private String calle;
    @Column(name = "num")
    private Integer num;
    @Basic(optional = false)
    @Column(name = "colonia")
    private String colonia;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "telefono")
    private Integer telefono;
    @Column(name = "celular")
    private Integer celular;
    @Basic(optional = false)
    @Column(name = "contacto_id")
    private int contactoId;

    public Operador() {
    }

    public Operador(Integer id) {
        this.id = id;
    }

    public Operador(Integer id, String nombre, String apePaterno, String apeMaterno, String calle, String colonia, String ciudad, int contactoId) {
        this.id = id;
        this.nombre = nombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.contactoId = contactoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public int getContactoId() {
        return contactoId;
    }

    public void setContactoId(int contactoId) {
        this.contactoId = contactoId;
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
        if (!(object instanceof Operador)) {
            return false;
        }
        Operador other = (Operador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Operador[ id=" + id + " ]";
    }
    
}
