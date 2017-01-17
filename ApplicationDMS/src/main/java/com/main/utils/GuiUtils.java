/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;

/**
 *
 * @author mayel-1
 */
public class GuiUtils {
    
    public static void handleLoading(ProgressBar progressBar, boolean visible){
        Platform.runLater(() -> {
            //ToolBar parent  = (ToolBar)progressBar.getParent().getParent();
            progressBar.setMaxWidth(Double.MAX_VALUE);
            progressBar.setVisible(visible);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuiUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        });                
    }
    
}
