/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.commons;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author hoben
 */
public class ModalDialog {

    public Stage createModal(Parent view, Node source) {
        //
        //create view container 
        //
        StackPane container = new StackPane();
        container.getChildren().clear();
        container.getChildren().add(view);
        
        Stage dialog = new Stage();
        dialog.setScene(new Scene(container));        
        dialog.initOwner(source.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        return dialog;
        
    }

}
