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
@Table(name = "Shopping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shopping.findAll", query = "SELECT s FROM Shopping s")
    , @NamedQuery(name = "Shopping.findById", query = "SELECT s FROM Shopping s WHERE s.id = :id")
    , @NamedQuery(name = "Shopping.findByName", query = "SELECT s FROM Shopping s WHERE s.name = :name")
    , @NamedQuery(name = "Shopping.findByDescription", query = "SELECT s FROM Shopping s WHERE s.description = :description")
    , @NamedQuery(name = "Shopping.findByDate", query = "SELECT s FROM Shopping s WHERE s.date = :date")
    , @NamedQuery(name = "Shopping.findByAmount", query = "SELECT s FROM Shopping s WHERE s.amount = :amount")})
public class Shopping implements Serializable {

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
    @Size(min = 1, max = 2000000000)
    @Column(name = "date")
    private String date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingId")
    private List<Shoppingdetail> shoppingdetailList;
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    @ManyToOne
    private Customer customerId;
    @JoinColumn(name = "supplierId", referencedColumnName = "id")
    @ManyToOne
    private Supplier supplierId;

    public Shopping() {
    }

    public Shopping(Integer id) {
        this.id = id;
    }

    public Shopping(Integer id, String name, String date, double amount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @XmlTransient
    public List<Shoppingdetail> getShoppingdetailList() {
        return shoppingdetailList;
    }

    public void setShoppingdetailList(List<Shoppingdetail> shoppingdetailList) {
        this.shoppingdetailList = shoppingdetailList;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
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
        if (!(object instanceof Shopping)) {
            return false;
        }
        Shopping other = (Shopping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helmidev.entities.Shopping[ id=" + id + " ]";
    }
    
}
