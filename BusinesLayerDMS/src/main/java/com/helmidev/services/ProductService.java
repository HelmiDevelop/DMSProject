/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.services;

import com.helmidev.entities.Category;
import com.helmidev.entities.Product;
import com.helmidev.jpacontrollers.ProductJpaController;
import com.helmidev.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoben
 */
public class ProductService {
    
    
    private final ProductJpaController productJpa;

    public ProductService() {
        
        productJpa = new ProductJpaController();
    }
    public void addProduct(Product product) throws Exception{
        productJpa.create(product);
    }
    public void updateProduct(Product product) throws Exception{
        productJpa.update(product);
    }
    public void removeProduct(Product product) throws NonexistentEntityException{
        try {
            productJpa.destroy(product);
        } catch (Exception ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Product> getAllProducts(){
        return productJpa.findProductEntities();
    }
    public List<Product> findProductByName(String name){
        List<Product> listofproductByName = new ArrayList<>();
        
        for (Product product:getAllProducts()){
            if(product.getName().equals(name)){
                listofproductByName.add(product);                
            }
        }
        return listofproductByName;
    }
    public List<Product> findProductByCategory(Category category){
        List<Product> listOfProductByCategory = new ArrayList<>();
        int i = 0;
        for (Product product:getAllProducts()){
            if(product.getCategoryId().equals(category)){
                listOfProductByCategory.add(i,product);
                i++;
            }
        }
        return listOfProductByCategory;
    }
    
}
