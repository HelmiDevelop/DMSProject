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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author mayel-1
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    
        try {
            BorderPane root = new BorderPane(new Label("Loading complete!"));
            DashboardView mainView = new DashboardView();
            //DashboardView appView = new DashboardView();
            
            Scene scene = new Scene(mainView.getView());
            stage.setTitle("DMS APP");
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
        DMSPreloader.launch(App.class, args);
        //launch(args);
    }
}
