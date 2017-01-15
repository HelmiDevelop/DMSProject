/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.utils;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author mayel-1
 */
public class GuiUtils {
    
    public static void handleLoading(ProgressBar progressBar, boolean visible){
        Platform.runLater(() -> {
            progressBar.setVisible(visible);            
        });                
    }
    
}
