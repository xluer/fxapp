package com.xu.fx;

import com.xu.util.LoginUtils;
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
    private static final String title = "酒店管理";
    private static final double width = 800.0;
    private static final double height = 600.0;

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private Stage stage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle(title);
        gotoLogin();
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public boolean userLogging(String userId, String password) {
        if (LoginUtils.check(userId, password)) {
            try {
                gotoManage();
            } catch (Exception e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public void exit() {
        Platform.exit();
    }

    private void gotoLogin() throws Exception {
        LoginController login = (LoginController) replaceSceneContent("Login.fxml");
        login.setApp(this);
    }

    private void gotoManage() throws Exception {
        ManageController manage = (ManageController) replaceSceneContent("Manage2.fxml");
        manage.setApp(this);
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream("/fxml/" + fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.getScene().getStylesheets().add("/styles/DatePicker.css");
        //stage.getScene().getStylesheets().add("/styles/menu.css");
        return loader.getController();
    }
}
