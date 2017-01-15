/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.jpacontrollers;

import java.io.Serializable;
import com.helmidev.entities.Product;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author hoben
 */
public class ProductJpaController extends AbstractController<Product> implements Serializable {

    public ProductJpaController() {
        super();
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            List<Product> products = em.createNativeQuery("SELECT * FROM tbl_product", Product.class).getResultList();
            return products;
        } finally {
            em.close();
        }
    }

    public Product findProduct(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            int count = (int) em.createNativeQuery("SELECT COUNT(ID) FROM tbl_product ID", int.class).getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

}
