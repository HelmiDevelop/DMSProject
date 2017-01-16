/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.management.product;

import com.helmidev.management.category.add.AddCategoryView;
import com.main.commons.ModalDialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author mayel-1
 */
public class ProductPresenter implements Initializable{

    
    @FXML Button addCategory;
    @FXML Button addPack;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       addCategory.setOnAction((event) -> {
           onaddCategoryClick(event);
       });
       addPack.setOnAction((event) -> {
           onaddPackClick(event);
       });
    }

    private void onaddCategoryClick(ActionEvent event) {
        ModalDialog dialogImpl = new ModalDialog();
        AddCategoryView catView = new AddCategoryView();
        Stage dialog = dialogImpl.createModal(catView.getView(),(Node) event.getSource());
        dialog.showAndWait();
    }

    private void onaddPackClick(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
