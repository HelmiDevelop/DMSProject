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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hoben
 */
@Entity
@Table(name = "Billing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billing.findAll", query = "SELECT b FROM Billing b")
    , @NamedQuery(name = "Billing.findById", query = "SELECT b FROM Billing b WHERE b.id = :id")
    , @NamedQuery(name = "Billing.findByName", query = "SELECT b FROM Billing b WHERE b.name = :name")
    , @NamedQuery(name = "Billing.findByDescription", query = "SELECT b FROM Billing b WHERE b.description = :description")
    , @NamedQuery(name = "Billing.findByDate", query = "SELECT b FROM Billing b WHERE b.date = :date")
    , @NamedQuery(name = "Billing.findByAmount", query = "SELECT b FROM Billing b WHERE b.amount = :amount")})
public class Billing implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billingId")
    private List<Billingdetail> billingdetailList;
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

    public Billing() {
    }

    public Billing(Integer id) {
        this.id = id;
    }

    public Billing(Integer id, String name, String date, double amount) {
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
    public List<Billingdetail> getBillingdetailList() {
        return billingdetailList;
    }

    public void setBillingdetailList(List<Billingdetail> billingdetailList) {
        this.billingdetailList = billingdetailList;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof Billing)) {
            return false;
        }
        Billing other = (Billing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helmidev.entities.Billing[ id=" + id + " ]";
    }
    
}
