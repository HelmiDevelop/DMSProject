/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.database.config;

import com.main.utils.DmsConfig;
import com.main.utils.JaxbConverter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.xml.bind.JAXBException;

/**
 *
 * @author mayel-1
 */
public class DatabaseConfigPresenter implements Initializable {

    @FXML
    private TextField url;
    @FXML
    private TextField dbName;
    @FXML
    CheckBox isNewDb;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> driver;
    @FXML
    private Button save;
    @FXML
    private Button check;
    @FXML
    private ImageView checkImage;

    ObservableList<String> drivers = FXCollections.observableArrayList();
    private boolean dbInitialized;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initView();        
    }
    
    private void initView(){
        drivers.add("SQLITE");
        drivers.add("MYSQL");
        Platform.runLater(() -> {
            this.driver.setItems(drivers);
            this.driver.getSelectionModel().select(0);
        });
        save.setOnAction((event) -> {
            onSaveClick(event);
        });
        check.setOnAction((event) -> {
            onCheckClick(event);
        });
    }
    
    public void fillToEdit(){
        JaxbConverter<PersitenceUnit> jaxbConverter = new JaxbConverter<>();
        PersitenceUnit pu = null;
        try {
            pu = jaxbConverter.readXML(PersitenceUnit.class, DmsConfig.DMS_DB_CONFIG_PATH);
        } catch (JAXBException ex) {
            Logger.getLogger(DatabaseConfigPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(pu != null){
            url.setText(pu.url);
            dbName.setText(pu.getDBNAME());
            if(pu.getJDBCDRIVER().equals(DmsConfig.JDBC_DRIVER_SQLITE))
                driver.getSelectionModel().select("SQLITE");
            else if (pu.getJDBCDRIVER().equals(DmsConfig.JDBC_DRIVER_MYSQL))
                driver.getSelectionModel().select("MYSQL");
        }
        this.isNewDb.setDisable(true);
    }

    private void onSaveClick(ActionEvent event) {
        PersitenceUnit persitenceUnit = new PersitenceUnit();
        persitenceUnit.setURL(url.getText());
        persitenceUnit.setDBNAME(dbName.getText());
        //persitenceUnit.setInitDb(Boolean.valueOf(isNewDb.getText()));
        persitenceUnit.setUSERNANE(username.getText());
        persitenceUnit.setPASSWORD(password.getText());
        // org.sqlite.JDBC
        if(((String) driver.getSelectionModel().getSelectedItem()).equals("SQLITE"))
            persitenceUnit.setJDBCDRIVER("org.sqlite.JDBC"); // sqlite driver name
        else
            persitenceUnit.setJDBCDRIVER("com.mysql.jdbc.Driver"); // mysql driver  name

        JaxbConverter<PersitenceUnit> jaxbConverter = new JaxbConverter<>();

        try {
            jaxbConverter.writeToFile(persitenceUnit, DmsConfig.DMS_DB_CONFIG_PATH);
            dbInitialized  = true;
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(DatabaseConfigPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void onCheckClick(ActionEvent event) {

    }

    public boolean isDbInitialized() {
        return dbInitialized;
    }

}
