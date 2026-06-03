package com.example.courseworkitfu.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTableParameters {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty username = new SimpleStringProperty();
    private final SimpleStringProperty password = new SimpleStringProperty();
    private final SimpleStringProperty email = new SimpleStringProperty();
    private final SimpleStringProperty dateCreated = new SimpleStringProperty();
    private final SimpleStringProperty isAdmin = new SimpleStringProperty();
    private final SimpleStringProperty isActive = new SimpleStringProperty();
    private final SimpleStringProperty phoneNum = new SimpleStringProperty();

    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty surname = new SimpleStringProperty();
    private final SimpleStringProperty cardNo = new SimpleStringProperty();
    private final SimpleIntegerProperty bonusPoints = new SimpleIntegerProperty();
    private final SimpleStringProperty clientAddress = new SimpleStringProperty();
    private final SimpleStringProperty dateOfBirth = new SimpleStringProperty();
    private final SimpleStringProperty balance = new SimpleStringProperty();

    private final SimpleStringProperty driverLicence = new SimpleStringProperty();
    private final SimpleStringProperty vehicleType = new SimpleStringProperty();
    private final SimpleStringProperty birthDate = new SimpleStringProperty();
    private final SimpleStringProperty isAvailable = new SimpleStringProperty();
    private final SimpleStringProperty driverRating = new SimpleStringProperty();
    private final SimpleIntegerProperty totalDeliveries = new SimpleIntegerProperty();
    private final SimpleStringProperty vehiclePlateNumber = new SimpleStringProperty();

    private final SimpleStringProperty description = new SimpleStringProperty();
    private final SimpleStringProperty restaurantAddress = new SimpleStringProperty();
    private final SimpleStringProperty happyHours = new SimpleStringProperty();
    private final SimpleStringProperty cuisineType = new SimpleStringProperty();
    private final SimpleStringProperty restaurantRating = new SimpleStringProperty();
    private final SimpleStringProperty isOpen = new SimpleStringProperty();
    private final SimpleStringProperty openingTime = new SimpleStringProperty();
    private final SimpleStringProperty closingTime = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDateCreated() {
        return dateCreated.get();
    }

    public SimpleStringProperty dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public String getIsAdmin() {
        return isAdmin.get();
    }

    public SimpleStringProperty isAdminProperty() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin.set(isAdmin);
    }

    public String getIsActive() {
        return isActive.get();
    }

    public SimpleStringProperty isActiveProperty() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive.set(isActive);
    }

    public String getPhoneNum() {
        return phoneNum.get();
    }

    public SimpleStringProperty phoneNumProperty() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum.set(phoneNum);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getCardNo() {
        return cardNo.get();
    }

    public SimpleStringProperty cardNoProperty() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo.set(cardNo);
    }

    public int getBonusPoints() {
        return bonusPoints.get();
    }

    public SimpleIntegerProperty bonusPointsProperty() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints.set(bonusPoints);
    }

    public String getClientAddress() {
        return clientAddress.get();
    }

    public SimpleStringProperty clientAddressProperty() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress.set(clientAddress);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getBalance() {
        return balance.get();
    }

    public SimpleStringProperty balanceProperty() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }

    public String getDriverLicence() {
        return driverLicence.get();
    }

    public SimpleStringProperty driverLicenceProperty() {
        return driverLicence;
    }

    public void setDriverLicence(String driverLicence) {
        this.driverLicence.set(driverLicence);
    }

    public String getVehicleType() {
        return vehicleType.get();
    }

    public SimpleStringProperty vehicleTypeProperty() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType.set(vehicleType);
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public SimpleStringProperty birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public String getIsAvailable() {
        return isAvailable.get();
    }

    public SimpleStringProperty isAvailableProperty() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable.set(isAvailable);
    }

    public String getDriverRating() {
        return driverRating.get();
    }

    public SimpleStringProperty driverRatingProperty() {
        return driverRating;
    }

    public void setDriverRating(String driverRating) {
        this.driverRating.set(driverRating);
    }

    public int getTotalDeliveries() {
        return totalDeliveries.get();
    }

    public SimpleIntegerProperty totalDeliveriesProperty() {
        return totalDeliveries;
    }

    public void setTotalDeliveries(int totalDeliveries) {
        this.totalDeliveries.set(totalDeliveries);
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber.get();
    }

    public SimpleStringProperty vehiclePlateNumberProperty() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber.set(vehiclePlateNumber);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getRestaurantAddress() {
        return restaurantAddress.get();
    }

    public SimpleStringProperty restaurantAddressProperty() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress.set(restaurantAddress);
    }

    public String getHappyHours() {
        return happyHours.get();
    }

    public SimpleStringProperty happyHoursProperty() {
        return happyHours;
    }

    public void setHappyHours(String happyHours) {
        this.happyHours.set(happyHours);
    }

    public String getCuisineType() {
        return cuisineType.get();
    }

    public SimpleStringProperty cuisineTypeProperty() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType.set(cuisineType);
    }

    public String getRestaurantRating() {
        return restaurantRating.get();
    }

    public SimpleStringProperty restaurantRatingProperty() {
        return restaurantRating;
    }

    public void setRestaurantRating(String restaurantRating) {
        this.restaurantRating.set(restaurantRating);
    }

    public String getIsOpen() {
        return isOpen.get();
    }

    public SimpleStringProperty isOpenProperty() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen.set(isOpen);
    }

    public String getOpeningTime() {
        return openingTime.get();
    }

    public SimpleStringProperty openingTimeProperty() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime.set(openingTime);
    }

    public String getClosingTime() {
        return closingTime.get();
    }

    public SimpleStringProperty closingTimeProperty() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime.set(closingTime);
    }
}
