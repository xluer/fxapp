package com.xu.fx;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageController extends Pane implements Initializable {

    private MainApp application;

    public void setApp(MainApp application) {
        this.application = application;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void exit() {
        application.exit();
    }
}
