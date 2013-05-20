package com.xu.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Login Controller.
 */
public class LoginController implements Initializable {

    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;

    private MainApp app;


    public void setApp(MainApp application) {
        this.app = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
        //userId.setPromptText("demo");
        //password.setPromptText("demo");
    }


    public void processLogin() {
        if (app == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello " + userId.getText());
        } else {
            if (!app.userLogging(userId.getText(), password.getText())) {
                errorMessage.setText("账号或密码不正确");
            }
        }
    }
}
