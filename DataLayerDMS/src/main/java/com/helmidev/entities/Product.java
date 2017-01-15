/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id")
    , @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")
    , @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description")
    , @NamedQuery(name = "Product.findBySPrice", query = "SELECT p FROM Product p WHERE p.sPrice = :sPrice")
    , @NamedQuery(name = "Product.findByPPrice", query = "SELECT p FROM Product p WHERE p.pPrice = :pPrice")
    , @NamedQuery(name = "Product.findByUnitSpan", query = "SELECT p FROM Product p WHERE p.unitSpan = :unitSpan")
    , @NamedQuery(name = "Product.findByIsPledge", query = "SELECT p FROM Product p WHERE p.isPledge = :isPledge")
    , @NamedQuery(name = "Product.findByPledgeValue", query = "SELECT p FROM Product p WHERE p.pledgeValue = :pledgeValue")})
public class Product implements Serializable {

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
    @Column(name = "sPrice")
    private double sPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pPrice")
    private double pPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unitSpan")
    private double unitSpan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isPledge")
    private int isPledge;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pledgeValue")
    private Double pledgeValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<Shoppingdetail> shoppingdetailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<Billingdetail> billingdetailList;
    @JoinColumn(name = "packagingId", referencedColumnName = "id")
    @ManyToOne
    private Packaging packagingId;
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    @ManyToOne
    private Category categoryId;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, double sPrice, double pPrice, double unitSpan, int isPledge) {
        this.id = id;
        this.name = name;
        this.sPrice = sPrice;
        this.pPrice = pPrice;
        this.unitSpan = unitSpan;
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

    public double getSPrice() {
        return sPrice;
    }

    public void setSPrice(double sPrice) {
        this.sPrice = sPrice;
    }

    public double getPPrice() {
        return pPrice;
    }

    public void setPPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public double getUnitSpan() {
        return unitSpan;
    }

    public void setUnitSpan(double unitSpan) {
        this.unitSpan = unitSpan;
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
    public List<Shoppingdetail> getShoppingdetailList() {
        return shoppingdetailList;
    }

    public void setShoppingdetailList(List<Shoppingdetail> shoppingdetailList) {
        this.shoppingdetailList = shoppingdetailList;
    }

    @XmlTransient
    public List<Billingdetail> getBillingdetailList() {
        return billingdetailList;
    }

    public void setBillingdetailList(List<Billingdetail> billingdetailList) {
        this.billingdetailList = billingdetailList;
    }

    public Packaging getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(Packaging packagingId) {
        this.packagingId = packagingId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helmidev.entities.Product[ id=" + id + " ]";
    }
    
}
