/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.jpacontrollers;

import com.helmidev.entities.Packaging;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author hoben
 */
public class PackagingJpaController extends AbstractController<Packaging> implements Serializable {

    public PackagingJpaController() {
        super();
    }

    public List<Packaging> findPackagingEntities() {
        return findPackagingEntities(true, -1, -1);
    }

    public List<Packaging> findPackagingEntities(int maxResults, int firstResult) {
        return findPackagingEntities(false, maxResults, firstResult);
    }

    private List<Packaging> findPackagingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            List<Packaging> packagings = em.createNativeQuery("SELECT * FROM tbl_packaging", Packaging.class).getResultList();
            return packagings;
        } finally {
            em.close();
        }
    }

    public Packaging findPackaging(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Packaging.class, id);
        } finally {
            em.close();
        }
    }

    public int getPackagingCount() {
        EntityManager em = getEntityManager();
        try {
            int count = (int) em.createNativeQuery("SELECT COUNT(ID) FROM tbl_packaging ID", int.class).getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

}
