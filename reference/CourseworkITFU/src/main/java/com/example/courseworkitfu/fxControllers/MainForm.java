package com.example.courseworkitfu.fxControllers;

import com.example.courseworkitfu.HelloApplication;
import com.example.courseworkitfu.hibernateOperations.CustomOperations;
import com.example.courseworkitfu.model.Client;
import com.example.courseworkitfu.model.Driver;
import com.example.courseworkitfu.model.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.courseworkitfu.model.User;
import javafx.scene.control.Tab;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainForm implements Initializable {

    @FXML
    public ListView<User> userList;
    @FXML
    public Tab userTab;
    @FXML
    public Tab ordersTab;
    @FXML
    public Tab statisticsTab;
    @FXML
    public TableView<UserTableParameters> usersTable;
    @FXML
    public TableColumn<UserTableParameters, Integer> idColumn;
    @FXML
    public TableColumn<UserTableParameters, String> usernameColumn;
    @FXML
    public TableColumn<UserTableParameters, String> passwordColumn;
    @FXML
    public TableColumn<UserTableParameters, String> emailColumn;
    @FXML
    public TableColumn<UserTableParameters, String> dateCreatedColumn;
    @FXML
    public TableColumn<UserTableParameters, String> adminColumn;
    @FXML
    public TableColumn<UserTableParameters, String> activeColumn;
    @FXML
    public TableColumn<UserTableParameters, String> phoneNumColumn;
    @FXML
    public TableColumn<UserTableParameters, String> nameColumn;
    @FXML
    public TableColumn<UserTableParameters, String> surnameColumn;
    @FXML
    public TableColumn<UserTableParameters, String> cardNoColumn;
    @FXML
    public TableColumn<UserTableParameters, Integer> bonusPointsColumn;
    @FXML
    public TableColumn<UserTableParameters, String> clientAddressColumn;
    @FXML
    public TableColumn<UserTableParameters, String> dateOfBirthColumn;
    @FXML
    public TableColumn<UserTableParameters, String> balanceColumn;
    @FXML
    public TableColumn<UserTableParameters, String> driverLicenceColumn;
    @FXML
    public TableColumn<UserTableParameters, String> vehicleTypeColumn;
    @FXML
    public TableColumn<UserTableParameters, String> birthDateColumn;
    @FXML
    public TableColumn<UserTableParameters, String> availableColumn;
    @FXML
    public TableColumn<UserTableParameters, String> driverRatingColumn;
    @FXML
    public TableColumn<UserTableParameters, Integer> totalDeliveriesColumn;
    @FXML
    public TableColumn<UserTableParameters, String> vehiclePlateNumberColumn;
    @FXML
    public TableColumn<UserTableParameters, String> descriptionColumn;
    @FXML
    public TableColumn<UserTableParameters, String> restaurantAddressColumn;
    @FXML
    public TableColumn<UserTableParameters, String> happyHoursColumn;
    @FXML
    public TableColumn<UserTableParameters, String> cuisineTypeColumn;
    @FXML
    public TableColumn<UserTableParameters, String> restaurantRatingColumn;
    @FXML
    public TableColumn<UserTableParameters, String> openColumn;
    @FXML
    public TableColumn<UserTableParameters, String> openingTimeColumn;
    @FXML
    public TableColumn<UserTableParameters, String> closingTimeColumn;

    private CustomOperations customOperations;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customOperations = new CustomOperations(HelloApplication.emf);
        usersTable.setEditable(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        cardNoColumn.setCellValueFactory(new PropertyValueFactory<>("cardNo"));
        bonusPointsColumn.setCellValueFactory(new PropertyValueFactory<>("bonusPoints"));
        clientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("clientAddress"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        driverLicenceColumn.setCellValueFactory(new PropertyValueFactory<>("driverLicence"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
        driverRatingColumn.setCellValueFactory(new PropertyValueFactory<>("driverRating"));
        totalDeliveriesColumn.setCellValueFactory(new PropertyValueFactory<>("totalDeliveries"));
        vehiclePlateNumberColumn.setCellValueFactory(new PropertyValueFactory<>("vehiclePlateNumber"));

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        restaurantAddressColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantAddress"));
        happyHoursColumn.setCellValueFactory(new PropertyValueFactory<>("happyHours"));
        cuisineTypeColumn.setCellValueFactory(new PropertyValueFactory<>("cuisineType"));
        restaurantRatingColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantRating"));
        openColumn.setCellValueFactory(new PropertyValueFactory<>("isOpen"));
        openingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("openingTime"));
        closingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("closingTime"));

        loadData();
    }

    private void loadUserList() {
        userList.getItems().clear();
        userList.getItems().addAll(customOperations.getAllRecords(User.class));
    }

    private void loadUserTable() {
        usersTable.getItems().clear();
        List<User> users = customOperations.getAllRecords(User.class);
        for (User u : users) {
            UserTableParameters userTableParameters = new UserTableParameters();
            userTableParameters.setId(u.getId());
            userTableParameters.setUsername(u.getUsername());
            userTableParameters.setPassword(u.getPassword());
            userTableParameters.setEmail(u.getEmail());
            userTableParameters.setDateCreated(toStringOrEmpty(u.getDateCreated()));
            userTableParameters.setIsAdmin(String.valueOf(u.isAdmin()));
            userTableParameters.setIsActive(String.valueOf(u.isActive()));
            userTableParameters.setPhoneNum(u.getPhoneNum());

            if (u instanceof Client client) {
                userTableParameters.setName(client.getName());
                userTableParameters.setSurname(client.getSurname());
                userTableParameters.setCardNo(client.getCardNo());
                userTableParameters.setBonusPoints(client.getBonusPoints());
                userTableParameters.setClientAddress(client.getAddress());
                userTableParameters.setDateOfBirth(toStringOrEmpty(client.getDateOfBirth()));
                userTableParameters.setBalance(String.valueOf(client.getBalance()));
            } else if (u instanceof Driver driver) {
                userTableParameters.setName(driver.getName());
                userTableParameters.setSurname(driver.getSurname());
                userTableParameters.setDriverLicence(driver.getDriverLicence());
                userTableParameters.setVehicleType(toStringOrEmpty(driver.getVehicleType()));
                userTableParameters.setBirthDate(toStringOrEmpty(driver.getBirthDate()));
                userTableParameters.setIsAvailable(String.valueOf(driver.isAvailable()));
                userTableParameters.setDriverRating(String.valueOf(driver.getRating()));
                userTableParameters.setTotalDeliveries(driver.getTotalDeliveries());
                userTableParameters.setVehiclePlateNumber(driver.getVehiclePlateNumber());
            } else if (u instanceof Restaurant restaurant) {
                userTableParameters.setDescription(restaurant.getDescription());
                userTableParameters.setRestaurantAddress(restaurant.getAddress());
                userTableParameters.setHappyHours(toStringOrEmpty(restaurant.getHappyHours()));
                userTableParameters.setCuisineType(
                        restaurant.getCuisineTypes() == null
                                ? ""
                                : restaurant.getCuisineTypes().stream()
                                .map(Enum::name)
                                .collect(Collectors.joining(", "))
                );
                userTableParameters.setRestaurantRating(String.valueOf(restaurant.getRating()));
                userTableParameters.setIsOpen(String.valueOf(restaurant.isOpen()));
                userTableParameters.setOpeningTime(toStringOrEmpty(restaurant.getOpeningTime()));
                userTableParameters.setClosingTime(toStringOrEmpty(restaurant.getClosingTime()));
            }

            usersTable.getItems().add(userTableParameters);
        }
    }


    public void loadData() {

        if (userTab.isSelected()) {
            loadUserList();
            loadUserTable();
        } else if (ordersTab.isSelected()) {
            //Fill in order related data
        } else if (statisticsTab.isSelected()) {
            //Provide animation/graphs
        }

    }

    public void loadRegForm() throws IOException {
        User user = userList.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create-user-form.fxml"));
        Parent parent = fxmlLoader.load();

        //Now I can access CreateUserForm controller String
        CreateUserForm createUserForm = fxmlLoader.getController();
        createUserForm.setData(false, user);

        Scene scene = new Scene(parent);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteUser() {
        User user = userList.getSelectionModel().getSelectedItem();
        customOperations.delete(user.getId(), User.class);
        loadData();
    }

    private String toStringOrEmpty(Object value) {
        return value == null ? "" : value.toString();
    }
}
