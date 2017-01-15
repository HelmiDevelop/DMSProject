/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.jpacontrollers;

import com.helmidev.entities.Category;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author hoben
 */
public class CategoryJpaController extends AbstractController<Category> implements Serializable {

    public CategoryJpaController() {
        super();
    }

    public List<Category> findAllCategories(){
        EntityManager em = getEntityManager();
        try{
            List<Category> categories = em.createNamedQuery("Category.findAll")
                    .getResultList();
            return categories;
        }finally{
            em.close();
        }
    }

    public Category findCategory(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            int count = (int) em.createNativeQuery("SELECT COUNT(ID) FROM tbl_category ID", int.class).getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

}
