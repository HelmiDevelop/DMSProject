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
@Table(name = "Shoppingdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shoppingdetail.findAll", query = "SELECT s FROM Shoppingdetail s")
    , @NamedQuery(name = "Shoppingdetail.findById", query = "SELECT s FROM Shoppingdetail s WHERE s.id = :id")
    , @NamedQuery(name = "Shoppingdetail.findByQuantity", query = "SELECT s FROM Shoppingdetail s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "Shoppingdetail.findBySubtotal", query = "SELECT s FROM Shoppingdetail s WHERE s.subtotal = :subtotal")})
public class Shoppingdetail implements Serializable {

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
    @JoinColumn(name = "shoppingId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shopping shoppingId;

    public Shoppingdetail() {
    }

    public Shoppingdetail(Integer id) {
        this.id = id;
    }

    public Shoppingdetail(Integer id, int quantity, double subtotal) {
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

    public Shopping getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Shopping shoppingId) {
        this.shoppingId = shoppingId;
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
        if (!(object instanceof Shoppingdetail)) {
            return false;
        }
        Shoppingdetail other = (Shoppingdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helmidev.entities.Shoppingdetail[ id=" + id + " ]";
    }
    
}
