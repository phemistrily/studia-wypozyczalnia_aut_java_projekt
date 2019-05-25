package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CarsTableModel {

    private SimpleIntegerProperty lp;
    private SimpleStringProperty name;
    private SimpleStringProperty carClass;
    private SimpleStringProperty brand;
    private SimpleStringProperty isRented;
    private SimpleStringProperty localisation;
    private SimpleIntegerProperty price_per_day;

    public CarsTableModel(Integer lp, String name, String carClass, String brand, String isRented, String localisation, Integer price_per_day) {
        this.lp = new SimpleIntegerProperty(lp);
        this.name = new SimpleStringProperty(name);
        this.carClass = new SimpleStringProperty(carClass);
        this.brand = new SimpleStringProperty(brand);
        this.isRented = new SimpleStringProperty(isRented);
        this.localisation = new SimpleStringProperty(localisation);
        this.price_per_day = new SimpleIntegerProperty(price_per_day);
    }

    public int getLp() {
        return lp.get();
    }

    public SimpleIntegerProperty lpProperty() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp.set(lp);
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

    public String getCarClass() {
        return carClass.get();
    }

    public SimpleStringProperty carClassProperty() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass.set(carClass);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getIsRented() {
        return isRented.get();
    }

    public SimpleStringProperty isRentedProperty() {
        return isRented;
    }

    public void setIsRented(String isRented) {
        this.isRented.set(isRented);
    }

    public String getLocalisation() {
        return localisation.get();
    }

    public SimpleStringProperty localisationProperty() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation.set(localisation);
    }

    public int getPrice_per_day() {
        return price_per_day.get();
    }

    public SimpleIntegerProperty price_per_dayProperty() {
        return price_per_day;
    }

    public void setPrice_per_day(int price_per_day) {
        this.price_per_day.set(price_per_day);
    }
}
