/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.database.config;

import com.main.utils.JaxbConverter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author mayel-1
 */
public class DatabaseConfigPresenter implements Initializable{
    
    @FXML private TextField url;
    @FXML private TextField dbName;
    @FXML CheckBox isNewDb;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private ChoiceBox driver;
    @FXML private Button save;
    @FXML private Button check;
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save.setOnAction((event) -> {
            onSaveClick(event);
        });
        check.setOnAction((event) -> {
            onCheckClick(event);
        });
    }

    private void onSaveClick(ActionEvent event) {
        PersitenceUnit persitenceUnit = new PersitenceUnit();
        persitenceUnit.setURL(url.getText());        
        persitenceUnit.setDbName(dbName.getText());
        persitenceUnit.setInitDb(Boolean.valueOf(isNewDb.getText()));
        persitenceUnit.setUSERNANE(username.getText());
        persitenceUnit.setPASSWORD(password.getText());
        persitenceUnit.setJDBCDRIVER((String)driver.getSelectionModel().getSelectedItem());
        
        JaxbConverter<PersitenceUnit> jaxbConverter = new JaxbConverter<>(PersitenceUnit.class);
        String xml;
        try {
            byte[] data = jaxbConverter.unmarshal(persitenceUnit);
            xml = new String(data);
        } catch (XMLStreamException | JAXBException ex) {
            Logger.getLogger(DatabaseConfigPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void onCheckClick(ActionEvent event) {
        
    }
    
}
