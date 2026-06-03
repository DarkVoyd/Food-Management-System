package com.example.courseworkitfu.fxControllers;

import com.example.courseworkitfu.HelloApplication;
import com.example.courseworkitfu.hibernateOperations.GenericOperations;
import com.example.courseworkitfu.model.Client;
import com.example.courseworkitfu.model.Cuisine;
import com.example.courseworkitfu.model.Driver;
import com.example.courseworkitfu.model.Restaurant;
import com.example.courseworkitfu.model.User;
import com.example.courseworkitfu.model.VehicleType;
import org.controlsfx.control.CheckComboBox;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.ResourceBundle;

public class CreateUserForm implements Initializable {

    public ToggleGroup userType;
    public RadioButton clientRadio;
    public RadioButton driverRadio;
    public RadioButton restaurantRadio;

    public Pane userPane;
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField repeatPasswordField;
    public TextField emailField;
    public TextField phoneField;

    public Pane clientPane;
    public TextField clientNameField;
    public TextField clientSurnameField;
    public TextField cardNoField;
    public TextField clientAddressField;
    public DatePicker clientBirthDatePicker;

    public Pane driverPane;
    public TextField driverNameField;
    public TextField driverSurnameField;
    public TextField driverLicenceField;
    public ComboBox<VehicleType> vehicleTypeCombo;
    public DatePicker driverBirthDatePicker;
    public TextField vehiclePlateField;

    public Pane restaurantPane;
    public TextArea restaurantDescField;
    public TextField restaurantAddressField;
    public CheckComboBox<Cuisine> cuisineTypeCombo;
    public TextField openingTimeField;
    public TextField closingTimeField;

    private GenericOperations genericOperations;

    public void setData(boolean isRegistration) {
        if (isRegistration) {
            clientPane.setDisable(true);
            clientPane.setVisible(false);
            driverPane.setDisable(true);
            driverPane.setVisible(false);
            restaurantPane.setDisable(false);
            restaurantPane.setVisible(true);
            clientRadio.setVisible(false);
            driverRadio.setVisible(false);
            restaurantRadio.setSelected(true);
            restaurantRadio.setVisible(false);
        }
    }

    public void setData(boolean isRegistration, User user){
        if(!isRegistration && user !=null){
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            repeatPasswordField.setText(user.getPassword());
            emailField.setText(user.getEmail());
            phoneField.setText(user.getPhoneNum());

            if (user instanceof Client client) {
                clientRadio.setSelected(true);
                radioChanged();
                clientNameField.setText(client.getName());
                clientSurnameField.setText(client.getSurname());
                cardNoField.setText(client.getCardNo());
                clientAddressField.setText(client.getAddress());
                clientBirthDatePicker.setValue(client.getDateOfBirth());
            } else if (user instanceof Driver driver) {
                driverRadio.setSelected(true);
                radioChanged();
                driverNameField.setText(driver.getName());
                driverSurnameField.setText(driver.getSurname());
                driverLicenceField.setText(driver.getDriverLicence());
                vehicleTypeCombo.setValue(driver.getVehicleType());
                driverBirthDatePicker.setValue(driver.getBirthDate());
                vehiclePlateField.setText(driver.getVehiclePlateNumber());
            } else if (user instanceof Restaurant restaurant) {
                restaurantRadio.setSelected(true);
                radioChanged();
                restaurantDescField.setText(restaurant.getDescription());
                restaurantAddressField.setText(restaurant.getAddress());
                cuisineTypeCombo.getCheckModel().clearChecks();
                if (restaurant.getCuisineTypes() != null) {
                    for (Cuisine cuisine : restaurant.getCuisineTypes()) {
                        cuisineTypeCombo.getCheckModel().check(cuisine);
                    }
                }
                openingTimeField.setText(
                        restaurant.getOpeningTime() != null ? restaurant.getOpeningTime().toString() : ""
                );
                closingTimeField.setText(
                        restaurant.getClosingTime() != null ? restaurant.getClosingTime().toString() : ""
                );
            }
        }
    }

    public void radioChanged() {
        clientPane.setDisable(true);
        clientPane.setVisible(false);
        driverPane.setDisable(true);
        driverPane.setVisible(false);
        restaurantPane.setDisable(true);
        restaurantPane.setVisible(false);

        if (clientRadio.isSelected()) {
            clientPane.setDisable(false);
            clientPane.setVisible(true);
        } else if (driverRadio.isSelected()) {
            driverPane.setDisable(false);
            driverPane.setVisible(true);
        } else if (restaurantRadio.isSelected()) {
            restaurantPane.setDisable(false);
            restaurantPane.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genericOperations = new GenericOperations(HelloApplication.emf);
        driverPane.setVisible(false);
        vehicleTypeCombo.getItems().addAll(VehicleType.values());
        cuisineTypeCombo.getItems().addAll(Cuisine.values());
    }

    public void createUser() {
        if (restaurantRadio.isSelected()) {
            Restaurant restaurant = new Restaurant(
                    usernameField.getText(),
                    passwordField.getText(),
                    phoneField.getText(),
                    restaurantDescField.getText(),
                    restaurantAddressField.getText(),
                    LocalDateTime.now(),
                    new HashSet<>(cuisineTypeCombo.getCheckModel().getCheckedItems()),
                    parseLocalTimeOrNull(openingTimeField.getText()),
                    parseLocalTimeOrNull(closingTimeField.getText())
            );
            restaurant.setEmail(emailField.getText());
            restaurant.setDateCreated(LocalDateTime.now());
            genericOperations.create(restaurant);
        } else if (clientRadio.isSelected()) {
            Client client = new Client(
                    usernameField.getText(),
                    passwordField.getText(),
                    phoneField.getText(),
                    cardNoField.getText(),
                    clientAddressField.getText(),
                    clientBirthDatePicker.getValue()
            );
            client.setName(clientNameField.getText());
            client.setSurname(clientSurnameField.getText());
            client.setEmail(emailField.getText());
            client.setDateCreated(LocalDateTime.now());
            genericOperations.create(client);
        } else if (driverRadio.isSelected()) {
            Driver driver = new Driver(
                    usernameField.getText(),
                    passwordField.getText(),
                    phoneField.getText(),
                    driverLicenceField.getText(),
                    vehicleTypeCombo.getValue(),
                    driverBirthDatePicker.getValue(),
                    vehiclePlateField.getText()
            );
            driver.setName(driverNameField.getText());
            driver.setSurname(driverSurnameField.getText());
            driver.setEmail(emailField.getText());
            driver.setDateCreated(LocalDateTime.now());
            genericOperations.create(driver);
        } else {
            User user = new User(
                    usernameField.getText(),
                    passwordField.getText(),
                    phoneField.getText(),
                    emailField.getText()
            );
            user.setDateCreated(LocalDateTime.now());
            genericOperations.create(user);
        }
    }

    private LocalTime parseLocalTimeOrNull(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return LocalTime.parse(value.trim());
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
