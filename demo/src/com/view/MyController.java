package com.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MyController {

    //所有控件

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwdField;

    @FXML
    private TextField userNameField;

    @FXML
    void login(ActionEvent event) {

        String UserName   = userNameField.getText();
        String UserPasswd   = passwdField.getText();
    }

}
