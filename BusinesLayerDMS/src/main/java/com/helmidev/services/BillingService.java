/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.services;

import com.helmidev.entities.Billing;
import com.helmidev.entities.Customer;
import com.helmidev.jpacontrollers.BillingJpaController;
import com.helmidev.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author hoben
 */
public class BillingService {

    private final BillingJpaController BillingJpa;

    public BillingService() {
        BillingJpa = new BillingJpaController();
    }

    public Billing addBilling(Billing billing) throws Exception {
        try {
            
                return BillingJpa.create(billing);
            
           
        } catch (Exception e) {
            throw e;
        }

        
    }

    public void updateBilling(Billing billing) throws Exception {
        
        BillingJpa.update(billing);
    }

    public void removeBilling(Billing billing) throws NonexistentEntityException {
        try {
            BillingJpa.destroy(billing);
        } catch (Exception ex) {
            Logger.getLogger(BillingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Billing> getAllBillings() {
        return BillingJpa.findAllBillings();
    }

    public List<Billing> findBillingByDate(String date) {
        return getAllBillings().stream()
                .filter(pred -> (pred.getDate().equals(date)))
                .collect(Collectors.toList());        
    }

    public List<Billing> findBillingByCustomer(Customer customer) {
        return getAllBillings().stream()
                .filter(pred -> (pred.getCustomerId().equals(customer)))
                .collect(Collectors.toList());
    }


}
