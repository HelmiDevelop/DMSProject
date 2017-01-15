/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.services;

import com.helmidev.entities.Packaging;
import com.helmidev.jpacontrollers.PackagingJpaController;
import com.helmidev.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hoben
 */
public class PackagingService {
    
    
    private final PackagingJpaController packJpa;

    public PackagingService() {
        
        packJpa = new PackagingJpaController();
    }
    public void addPackaging(Packaging packaging) throws Exception{
        try {
            packJpa.create(packaging);
        } catch (Exception ex) {
            throw  ex;
        }
    }
    public void updatePackaging(Packaging packaging) throws Exception{
        packJpa.update(packaging);
    }
    public void removePackaging(Packaging packaging) throws NonexistentEntityException{
        try {
            packJpa.destroy(packaging);
        } catch (Exception ex) {
            Logger.getLogger(PackagingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Packaging> getAllPackaging(){
        return packJpa.findPackagingEntities();
    }
    
    
    public Packaging findPackagingByName(String name){
        for (Packaging pack:getAllPackaging()){
         if (pack.getName().equals(name)){
             return pack;
         }
        }
        System.out.println("Packaging "+ name + " is not found");
        return null;
    }
    public List<Packaging> findPledgePackaging(){
        List<Packaging> listofPledgePackaging = new ArrayList<>();
        int i = 0;
        for (Packaging pack:getAllPackaging()){
            if (pack.getIsPledge()== 1){
                listofPledgePackaging.add(i,pack);
                i++;
            } else {
            }
        }   
        return listofPledgePackaging;
    }
    public List<Packaging> findPackagingBySize(int size){
        List<Packaging> listofPackagingBySize = new ArrayList<>();
        int i = 0;
        for (Packaging pack:getAllPackaging()){
            if(pack.getSize()== size){
                listofPackagingBySize.add(i, pack);
                i++;
            }
        }
        
        return listofPackagingBySize;
    }
    
}
