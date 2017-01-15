/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hoben
 */
@Entity
@Table(name = "Packaging")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Packaging.findAll", query = "SELECT p FROM Packaging p")
    , @NamedQuery(name = "Packaging.findById", query = "SELECT p FROM Packaging p WHERE p.id = :id")
    , @NamedQuery(name = "Packaging.findByName", query = "SELECT p FROM Packaging p WHERE p.name = :name")
    , @NamedQuery(name = "Packaging.findByDescription", query = "SELECT p FROM Packaging p WHERE p.description = :description")
    , @NamedQuery(name = "Packaging.findBySize", query = "SELECT p FROM Packaging p WHERE p.size = :size")
    , @NamedQuery(name = "Packaging.findByPrice", query = "SELECT p FROM Packaging p WHERE p.price = :price")
    , @NamedQuery(name = "Packaging.findByIsPledge", query = "SELECT p FROM Packaging p WHERE p.isPledge = :isPledge")
    , @NamedQuery(name = "Packaging.findByPledgeValue", query = "SELECT p FROM Packaging p WHERE p.pledgeValue = :pledgeValue")})
public class Packaging implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "name")
    private String name;
    @Size(max = 2000000000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "size")
    private int size;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isPledge")
    private int isPledge;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pledgeValue")
    private Double pledgeValue;
    @OneToMany(mappedBy = "packagingId")
    private List<Product> productList;

    public Packaging() {
    }

    public Packaging(Integer id) {
        this.id = id;
    }

    public Packaging(Integer id, String name, int size, double price, int isPledge) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.isPledge = isPledge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIsPledge() {
        return isPledge;
    }

    public void setIsPledge(int isPledge) {
        this.isPledge = isPledge;
    }

    public Double getPledgeValue() {
        return pledgeValue;
    }

    public void setPledgeValue(Double pledgeValue) {
        this.pledgeValue = pledgeValue;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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
        if (!(object instanceof Packaging)) {
            return false;
        }
        Packaging other = (Packaging) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helmidev.entities.Packaging[ id=" + id + " ]";
    }
    
}
