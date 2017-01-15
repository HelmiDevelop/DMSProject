
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.jpacontrollers;

import com.helmidev.entities.Billing;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 *
 * @author hoben
 */
public class BillingJpaController extends AbstractController<Billing> implements Serializable {

    public BillingJpaController() {
        super();
    }

    

    public List<Billing> findAllBillings() {
        EntityManager em = getEntityManager();
        try {
            List<Billing> billings = em.createNamedQuery("Billing.findAll")
                    .getResultList();
            return billings;
        } finally {
            em.close();
        }
    }
    
    public List<Billing> findBillingByName() {
        EntityManager em = getEntityManager();
        try{
            List<Billing> billings = em.createNamedQuery("Billing.findByName")
                    .getResultList();
            return billings;
        }finally {
            em.close();
        }
    }
    
    public List<Billing> findBillingByDate() {
        EntityManager em = getEntityManager();
        try{
            List<Billing> billings = em.createNamedQuery("Billing.findByDate")
                    .getResultList();
            return billings;
        }finally {
            em.close();
        }
    }
   

}
