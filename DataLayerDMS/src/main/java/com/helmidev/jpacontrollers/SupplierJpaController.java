/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.jpacontrollers;

import com.helmidev.entities.Supplier;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author hoben
 */
public class SupplierJpaController extends AbstractController<Supplier> implements Serializable {

    public SupplierJpaController() {
        super();
    }

    public List<Supplier> findSupplierEntities() {
        return findSupplierEntities(true, -1, -1);
    }

    public List<Supplier> findSupplierEntities(int maxResults, int firstResult) {
        return findSupplierEntities(false, maxResults, firstResult);
    }

    private List<Supplier> findSupplierEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            List<Supplier> suppliers = em.createNativeQuery("SELECT * FROM tbl_supplier", Supplier.class).getResultList();
            return suppliers;
        } finally {
            em.close();
        }
    }

    public Supplier findSupplier(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Supplier.class, id);
        } finally {
            em.close();
        }
    }

    public int getSupplierCount() {
        EntityManager em = getEntityManager();
        try {
            int count = (int) em.createNativeQuery("SELECT COUNT(ID) FROM tbl_supplier ID", int.class).getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

}
