/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.management.category.overview;

import com.helmidev.entities.Category;
import com.helmidev.management.category.add.AddCategoryPresenter;
import com.helmidev.management.category.add.AddCategoryView;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    
    @FXML AnchorPane addcategoryPane;
    AddCategoryPresenter addcategorypresenter;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AddCategoryView addView = new AddCategoryView();
        Object presenter = addView.getPresenter();
        this.addcategorypresenter = (AddCategoryPresenter) presenter;
        addcategorypresenter.doNotDisplayCancelButton();
        for (Node node :addView.getView().getChildrenUnmodifiable() ){
            if (node instanceof Button){
               if( ((Button) node).isCancelButton()){
                   ((Button) node).setDisable(true);
               } 
            }
        }
        Parent view = addView.getView();
        
        this.addcategoryPane.getChildren().clear();
        this.addcategoryPane.getChildren().add(view);
        editCategory.setOnAction((ActionEvent event)  -> {
            onEditClick(event);
        });
        deleteCategory.setOnAction ((ActionEvent event)-> {
            onDeleteClick(event); 
        });
    }
    
    
    private void onEditClick(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void onDeleteClick(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
