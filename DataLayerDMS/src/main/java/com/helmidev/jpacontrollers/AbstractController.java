/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.jpacontrollers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author hoben
 * @param <T>
 */
public abstract class AbstractController<T> {

    protected EntityManagerFactory emf;

    public AbstractController() {
        emf = Persistence.createEntityManagerFactory("DMSPU");
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public T create(T entity) throws Exception {
        EntityManager em = getEntityManager();
        try {

            EntityTransaction tx = em.getTransaction();

            tx.begin();
            if (!em.contains(entity)) {
                em.persist(entity);
                tx.commit();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return entity;
    }

    public T update(T entity) throws Exception {
        EntityManager em = getEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(entity);
            tx.commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return entity;
    }

    public void destroy(T entity) throws Exception{
        EntityManager em = getEntityManager();
        T current = null;
        try {
            em.getTransaction().begin();
            if (!em.contains(entity)) {
                current = em.merge(entity);
            }
            em.remove(current);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}