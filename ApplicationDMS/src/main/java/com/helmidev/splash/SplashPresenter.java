/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.splash;

import com.main.utils.DMSConfigType;
import com.main.utils.ImageNames;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 *
 * @author mayel-1
 */
public class SplashPresenter implements Initializable{

    
    @FXML Label informationLabel;
    @FXML VBox messagesVbox;
    private DMSConfigType configType;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        informationLabel.setText("DMS Initialisierung ...");
    }
    public void updateMessage(String message){
        Label msg = new Label(message);
        ImageView imageView = new ImageView(ImageNames.CHEVRONRIGHT.Name());        
        msg.setGraphic(imageView);        
        msg.setTextFill(Paint.valueOf("fffdfd"));
        messagesVbox.getChildren().add(msg);
    }

    public DMSConfigType getConfigType() {
        return configType;
    }

    public void setConfigType(DMSConfigType configType) {
        this.configType = configType;
    }
    
}
