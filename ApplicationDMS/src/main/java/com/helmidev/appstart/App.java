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
import com.helmidev.splash.SplashPresenter;
import com.main.utils.DmsConfig;
import com.main.utils.JaxbConverter;
import com.helmidev.utils.PersistenceMap;
import com.main.commons.ModalDialog;
import com.main.utils.ImageNames;
import java.io.File;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.xml.bind.JAXBException;

/**
 *
 * @author mayel-1
 */
public class App extends Application {

    private DatabaseConfigPresenter databaseConfigPresenter;
    private Stage stage;
    DMSPreloader preloader;

    public boolean isDatabaseConfigExists() {
        File dbConfigFile = new File(DmsConfig.DMS_DB_CONFIG_PATH);
        if (!dbConfigFile.exists()) {
            return false;
        }
        return true;
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        try {
            preloader = new DMSPreloader();
            
            preloader.startSplash(new SplashConsumer());
                       
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean initConfiguration(SplashPresenter splashPresenter) throws InterruptedException {
        switch(splashPresenter.getConfigType()){
            case DATABASE:
                return initDbConnection(splashPresenter);
            default:
                return initTest(splashPresenter);
        }        
    }

    boolean initDbConnection(SplashPresenter splashPresenter) throws InterruptedException {
        boolean initialized = false;
        if (this.isDatabaseConfigExists()) {
            splashPresenter.updateMessage("Database config exists");            
            //TODO lauch application
            initialized = true;
            try {

            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            DatabaseConfigView configView = new DatabaseConfigView();
            databaseConfigPresenter = (DatabaseConfigPresenter) configView.getPresenter();
            ModalDialog modalDialog = new ModalDialog();
            Stage configStage = modalDialog.createModal(configView.getView(), (Node) preloader.getSplashStage().getScene().getRoot());
            configStage.showAndWait();
            initialized = databaseConfigPresenter.isDbInitialized();
            if(!initialized){
                try {
                    preloader.stop();
                } catch (Exception ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return initialized;
    }

    private void lauchMain() {
        if (this.stage != null) {
            stage.initStyle(StageStyle.DECORATED);

            try {
                DashboardView mainView = new DashboardView();
                Scene scene = new Scene(mainView.getView());
                stage.setTitle("DMS APP");
                stage.setIconified(true);
                stage.getIcons().add(new Image(ImageNames.LAUNCH_3X.Name()));
                final String uri = getClass().getResource("/styles/app.css").toExternalForm();
                scene.getStylesheets().add(uri);
                stage.setScene(scene);
                stage.setOnShowing((event) -> {
                    try {
                        preloader.stop();
                    } catch (Exception ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                JaxbConverter<PersitenceUnit> jaxbConverter = new JaxbConverter<>();
                PersitenceUnit persitenceUnit = jaxbConverter.readXML(PersitenceUnit.class, DmsConfig.DMS_DB_CONFIG_PATH);
                PersistenceMap.CreatePersistencePropertyMap(
                        persitenceUnit.getURL(),
                        persitenceUnit.getDBNAME(),
                        persitenceUnit.getUSERNANE(),
                        persitenceUnit.getPASSWORD(),
                        persitenceUnit.getJDBCDRIVER());
                stage.show();
            } catch (JAXBException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    boolean initTest(SplashPresenter presenter) throws InterruptedException {

        SplashService splashService = new SplashService();
        splashService.setMessageValue("Init Test");
        splashService.setOnSucceeded((event) -> {
            presenter.updateMessage((String) event.getSource().getValue());
        });
        splashService.start();
        
        return true;
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        DMSPreloader.launch(App.class, args);
        //launch(args);
    }   

    private class SplashConsumer implements BiConsumer<WindowEvent, SplashPresenter> {

        public SplashConsumer() {
        }

        @Override
        public void accept(WindowEvent event, SplashPresenter splashPresenter) {
            try {
                boolean res = initConfiguration(splashPresenter);
                DMSPreloader.configs.put(splashPresenter.getConfigType(), true);
                System.out.println(splashPresenter.getConfigType().toString());
                if(!DMSPreloader.configs.values().contains(Boolean.FALSE)){
                    lauchMain();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
