/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hoben
 */
@Entity
@Table(name = "Billingdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billingdetail.findAll", query = "SELECT b FROM Billingdetail b")
    , @NamedQuery(name = "Billingdetail.findById", query = "SELECT b FROM Billingdetail b WHERE b.id = :id")
    , @NamedQuery(name = "Billingdetail.findByQuantity", query = "SELECT b FROM Billingdetail b WHERE b.quantity = :quantity")
    , @NamedQuery(name = "Billingdetail.findBySubtotal", query = "SELECT b FROM Billingdetail b WHERE b.subtotal = :subtotal")})
public class Billingdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private double subtotal;
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "billingId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Billing billingId;

    public Billingdetail() {
    }

    public Billingdetail(Integer id) {
        this.id = id;
    }

    public Billingdetail(Integer id, int quantity, double subtotal) {
        this.id = id;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Billing getBillingId() {
        return billingId;
    }

    public void setBillingId(Billing billingId) {
        this.billingId = billingId;
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
        if (!(object instanceof Billingdetail)) {
            return false;
        }
        Billingdetail other = (Billingdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helmidev.entities.Billingdetail[ id=" + id + " ]";
    }
    
}
