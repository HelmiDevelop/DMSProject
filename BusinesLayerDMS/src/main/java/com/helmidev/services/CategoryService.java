/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.services;

import com.helmidev.jpacontrollers.CategoryJpaController;
import com.helmidev.entities.Category;
import com.helmidev.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Helmi Omrane
 */
public class CategoryService {

    private final CategoryJpaController categoryJpa;

    public CategoryService() {

        categoryJpa = new CategoryJpaController();
    }

    public void addCategory(Category category) {
        try {
            categoryJpa.create(category);
        } catch (Exception ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCategory(Category category) {
        try {
            categoryJpa.update(category);
        } catch (Exception ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeCategory(Category category) throws NonexistentEntityException {
        try {
            categoryJpa.destroy(category);
        } catch (Exception ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Category> getAllCategories() {
        return categoryJpa.findAllCategories();
    }

    public Category findCategoryByName(String name) {

        return categoryJpa.findAllCategories().stream()
                .filter(predicate -> predicate.getName().equals(name))
                .findFirst().get();
    }

}
