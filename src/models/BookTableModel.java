package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookTableModel {

    private SimpleIntegerProperty lp;
    private SimpleStringProperty name;
    private SimpleStringProperty start_date;
    private SimpleStringProperty end_date;
    private SimpleStringProperty start_city;
    private SimpleStringProperty end_city;

    public BookTableModel(Integer lp, String name, String start_date, String end_date, String start_city, String end_city) {
        this.lp = new SimpleIntegerProperty(lp);
        this.name = new SimpleStringProperty(name);
        this.start_date = new SimpleStringProperty(start_date);
        this.end_date = new SimpleStringProperty(end_date);
        this.start_city = new SimpleStringProperty(start_city);
        this.end_city = new SimpleStringProperty(end_city);
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

    public String getStart_date() {
        return start_date.get();
    }

    public SimpleStringProperty start_dateProperty() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date.set(start_date);
    }

    public String getEnd_date() {
        return end_date.get();
    }

    public SimpleStringProperty end_dateProperty() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date.set(end_date);
    }

    public String getStart_city() {
        return start_city.get();
    }

    public SimpleStringProperty start_cityProperty() {
        return start_city;
    }

    public void setStart_city(String start_city) {
        this.start_city.set(start_city);
    }

    public String getEnd_city() {
        return end_city.get();
    }

    public SimpleStringProperty end_cityProperty() {
        return end_city;
    }

    public void setEnd_city(String end_city) {
        this.end_city.set(end_city);
    }
}
