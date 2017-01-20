package com.helmidev.appstart;

/*
 * #%L
 * igniter
 * %%
 * Copyright (C) 2013 - 2016 Adam Bien
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
//import com.airhacks.followme.dashboard.DashboardView;
import com.airhacks.afterburner.injection.Injector;
import com.helmidev.dashboard.DashboardView;
import com.helmidev.database.config.DatabaseConfigPresenter;
import com.helmidev.database.config.DatabaseConfigView;
import com.helmidev.database.config.PersitenceUnit;
import com.main.commons.ModalDialog;
import com.main.utils.DmsConfig;
import com.main.utils.JaxbConverter;
import com.helmidev.utils.PersistenceMap;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;

/**
 *
 * @author mayel-1
 */
public class App extends Application {

    private DatabaseConfigPresenter databaseConfigPresenter;

    public boolean isDatabaseConfigExists() {
        File dbConfigFile = new File(DmsConfig.DMS_DB_CONFIG_PATH);
        if (!dbConfigFile.exists()) {
            return false;
        }
        return true;
    }
    public boolean DatabaseExists(String path){
        boolean exist = false;
        File dataBase = new File(path);
        if(dataBase.exists()){
            exist = true;
        }
        return exist;
    }

    @Override
    public void start(Stage stage) throws Exception {

        try {
            /* check DB config. check if file <USER>/DMS/DB/dmsDB.sqlite exists.
                if not load db config db view to create one 
             */
            if (!isDatabaseConfigExists()) {
                    // no db config , ... create one and start the stage
                    DatabaseConfigView configView = new DatabaseConfigView();
                    databaseConfigPresenter = (DatabaseConfigPresenter) configView.getPresenter();
                    ModalDialog modalDialog = new ModalDialog();
                    Stage configStage = modalDialog.createModal(configView.getView(), ((Stage)event.getSource()).getScene().getRoot());
                    configStage.showAndWait();
                    configStage.setOnHidden((hiddenevent) -> {
                       stage.hide();
                       //
                       //restart the app
                       //
                       stage.show();
                    });
                }else{
                    JaxbConverter<PersitenceUnit> jaxbConverter = new JaxbConverter<>();
                    try {
                        PersitenceUnit persitenceUnit = jaxbConverter.readXML(PersitenceUnit.class, DmsConfig.DMS_DB_CONFIG_PATH);
                        if (DatabaseExists(persitenceUnit.getURL()+File.separator + persitenceUnit.getDBNAME())){
                            PersistenceMap.CreatePersistencePropertyMap(
                                persitenceUnit.getURL(), 
                                persitenceUnit.getDBNAME(), 
                                persitenceUnit.getUSERNANE(), 
                                persitenceUnit.getPASSWORD(), 
                                persitenceUnit.getJDBCDRIVER());
                        }else{
                            PersistenceMap.CreatePersistencePropertyMap(persitenceUnit.getURL(), 
                                persitenceUnit.getDBNAME()+";create = true", 
                                persitenceUnit.getUSERNANE(), 
                                persitenceUnit.getPASSWORD(), 
                                persitenceUnit.getJDBCDRIVER());
                        }
                        
                    } catch (JAXBException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            DashboardView mainView = new DashboardView();
            Scene scene = new Scene(mainView.getView());
            stage.setTitle("DMS APP");
            stage.setIconified(true);
            stage.getIcons().add(new Image(getClass().getResource("/images/ic_launch_3x.png").toExternalForm()));
            final String uri = getClass().getResource("/styles/app.css").toExternalForm();
            scene.getStylesheets().add(uri);
            stage.setScene(scene);
            
            stage.show();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(App.class, args);
        //launch(args);
    }
}
