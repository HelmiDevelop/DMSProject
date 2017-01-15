/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.services;

import com.helmidev.utils.DMSUtils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoben
 */
public class ExportService {   
    

    
    public boolean exportToPDF(){
        try(DMSUtils utils = new DMSUtils()){
            return utils.exportToPDF();
        } catch (IOException ex) {
            Logger.getLogger(ExportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
}
