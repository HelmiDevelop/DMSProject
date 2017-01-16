/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.management.category.add;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author hoben
 */
public class AddCategoryPresenter implements Initializable {

    @FXML private TextField categoryName;
    @FXML private TextArea categoryDesc;
    
    @FXML Button saveButton;
    @FXML Button cancelButton;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnAction((ActionEvent event) -> {
            onSaveClick(event);
        });
        cancelButton.setOnAction((ActionEvent event) -> {
            onCancelClick(event);
        });
    }

    private void onSaveClick(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void onCancelClick(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
