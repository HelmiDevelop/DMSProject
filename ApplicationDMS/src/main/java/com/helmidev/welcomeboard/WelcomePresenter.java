/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.welcomeboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author mayel-1
 */
public class WelcomePresenter implements Initializable{
    
    @FXML WebView estudView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        WebEngine we = estudView.getEngine();
        we.load("http://www.es.tu-darmstadt.de/es/das-fachgebiet/");
    }
    
}
