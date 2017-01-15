/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.jpacontrollers;

import java.io.Serializable;
import java.util.List;
import com.helmidev.entities.Customer;

import javax.persistence.EntityManager;

/**
 *
 * @author hoben
 */
public class CustomerJpaController extends AbstractController<Customer> implements Serializable {

    public CustomerJpaController() {
        super();
    }

    public List<Customer> findAllCustomers() {
        EntityManager em = getEntityManager();
        try {

            List<Customer> customers = em.createNamedQuery("Customer.findAll").getResultList();
            return customers;
        } finally {
            em.close();
        }
    }

    public Customer findCustomer(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            int count = (int) em.createNativeQuery("SELECT COUNT(ID) FROM tbl_customer ID", int.class).getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

}
