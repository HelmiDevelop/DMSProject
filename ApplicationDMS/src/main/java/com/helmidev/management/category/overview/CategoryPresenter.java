/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.management.category.overview;

import com.helmidev.entities.Category;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hoben
 */
public class CategoryPresenter implements Initializable {
    
    @FXML private TableView categoriesTable;
    @FXML TableColumn<Category, Integer> idcolumn;
    @FXML TableColumn<Category, String> namecolumn;
    @FXML TableColumn<Category, String> descriptioncolumn;
    
    @FXML Button editCategory;
    @FXML Button deleteCategory;
    
    @FXML AnchorPane addcategory;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editCategory.setOnAction((ActionEvent event)  -> {
            onEditClick();
        });
        deleteCategory.setOnAction ((ActionEvent event)-> {
            onDeleteClick(); 
        });
    }
    
    
    private void onEditClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void onDeleteClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
