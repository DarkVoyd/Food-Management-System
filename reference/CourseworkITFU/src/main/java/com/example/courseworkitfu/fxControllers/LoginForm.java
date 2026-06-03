package com.example.courseworkitfu.fxControllers;

import com.example.courseworkitfu.HelloApplication;
import com.example.courseworkitfu.hibernateOperations.CustomOperations;
import com.example.courseworkitfu.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginForm {

    public TextField loginField;
    public PasswordField passwordField;

    public void validateAndLogin() throws IOException {
        CustomOperations customOperations = new CustomOperations(HelloApplication.emf);
        User user = customOperations.getUserByCredentials(loginField.getText(), passwordField.getText());
        if (user != null) {
            //Jump to main window
            Stage stage = (Stage) loginField.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Info");
            alert.setContentText("Wrong credentials");

            alert.showAndWait();
        }
    }

    public void loadRegForm() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create-user-form.fxml"));
        Parent parent = fxmlLoader.load();

        //Now I can access CreateUserForm controller String
        CreateUserForm createUserForm = fxmlLoader.getController();
        createUserForm.setData(true);

        Scene scene = new Scene(parent);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
