/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.jpacontrollers;

import java.io.Serializable;
import com.helmidev.entities.Shopping;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author hoben
 */
public class ShoppingJpaController extends AbstractController<Shopping> implements Serializable {

    public ShoppingJpaController() {
        super();
    }

    public List<Shopping> findShoppingEntities() {
        return findShoppingEntities(true, -1, -1);
    }

    public List<Shopping> findShoppingEntities(int maxResults, int firstResult) {
        return findShoppingEntities(false, maxResults, firstResult);
    }

    private List<Shopping> findShoppingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            List<Shopping> shoppings = em.createNativeQuery("SELECT * FROM tbl_shopping", Shopping.class).getResultList();
            return shoppings;
        } finally {
            em.close();
        }
    }

    public Shopping findShopping(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Shopping.class, id);
        } finally {
            em.close();
        }
    }

    public int getShoppingCount() {
        EntityManager em = getEntityManager();
        try {
            int count = (int) em.createNativeQuery("SELECT COUNT(ID) FROM tbl_shpping ID", int.class).getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

}
