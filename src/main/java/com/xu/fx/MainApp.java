package com.xu.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private Stage stage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("酒店管理");
        stage.setMinWidth(390.0);
        stage.setMinHeight(500.0);
        gotoLogin();
        stage.show();
    }

    public boolean userLogging(String userId, String password) {
        if ("admin".equals(userId) && "123".equals(password)) {
            gotoManage();
            return true;
        } else {
            return false;
        }
    }

    public void exit() {
        Platform.exit();
    }

    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    private void gotoManage() {
        try {
            ManageController manage = (ManageController) replaceSceneContent("Manage.fxml");
            manage.setApp(this);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream("/fxml/"+fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.getScene().getStylesheets().add("/styles/menu.css");
        return loader.getController();
    }
}
