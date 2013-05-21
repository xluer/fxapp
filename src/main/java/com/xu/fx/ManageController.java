package com.xu.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    private Logger logger = LoggerFactory.getLogger(ManageController.class);
    private MainApp application;
    private AnchorPane curPane;
    @FXML
    AnchorPane central_panel;

    public void setApp(MainApp application) {
        this.application = application;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void exit() {
        application.exit();
    }

    private void changePane(String fn) {
        central_panel.getChildren().clear();
        try {
            curPane = FXMLLoader.load(getClass().getResource("/fxml/" + fn + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        central_panel.getChildren().add(curPane);
    }

    public void reserveList() {
        changePane("ReserveList");
    }

    public void newCustomer() {
        changePane("CustomerInfo");
    }

    public void testPop(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(MainApp.class.getResource("/fxml/AllInfo.fxml"));
        } catch (IOException e) {
            logger.error("io err", e);
        }
        stage.setScene(new Scene(root));
        stage.setTitle("");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }
}
