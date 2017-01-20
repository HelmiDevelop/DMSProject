/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helmidev.appstart;

import com.helmidev.splash.SplashPresenter;
import com.helmidev.splash.SplashView;
import com.main.utils.DMSConfigType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author mayel-1
 */
public class DMSPreloader extends Application {

    Stage stage;
    SplashPresenter splashPresenter;
    public static Map<DMSConfigType, Boolean> configs;

    private Scene createPreloaderScene() {
        SplashView splashView = new SplashView();
        splashPresenter = (SplashPresenter) splashView.getPresenter();
        return new Scene(splashView.getView());
    }

    @Override
    public void start(Stage stage) throws Exception {
        configs = new HashMap();
        configs.put(DMSConfigType.DATABASE, Boolean.FALSE);
        configs.put(DMSConfigType.UNPMAPPED, Boolean.FALSE);
        stage.setScene(createPreloaderScene());

    }

    @Override
    public void stop() throws Exception {
        this.stage.close();
    }

    public static void main(String[] args) {
        
        DMSPreloader.launch(App.class, args);
        //launch(args);
    }

    public void startSplash(BiConsumer<WindowEvent, SplashPresenter> consumer) {
        try {
            this.stage = new Stage(StageStyle.UNDECORATED);
            this.start(stage);
            this.stage.addEventHandler(WindowEvent.WINDOW_SHOWN, (WindowEvent event) -> {
                for (Map.Entry type : configs.entrySet()) {
                    Platform.runLater(() -> { 
                        splashPresenter.setConfigType((DMSConfigType)type.getKey());
                        consumer.accept(event, splashPresenter);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(DMSPreloader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }                

            });
            Platform.runLater(() -> {
                stage.show();
            });

        } catch (Exception ex) {
            Logger.getLogger(DMSPreloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stage getSplashStage() {
        return this.stage;
    }

}
