import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.Date;

public class Review {

    private SimpleStringProperty username;
    private SimpleStringProperty nationality;
    private SimpleIntegerProperty rating;
    private SimpleStringProperty text;
    private SimpleObjectProperty<LocalDate> date;
    private SimpleStringProperty hotelName;
    private SimpleStringProperty cityName;
    private SimpleStringProperty countryName;

    public Review(){
        this.username = new SimpleStringProperty("");
        this.nationality = new SimpleStringProperty("");
        this.rating = new SimpleIntegerProperty(0);
        this.text = new SimpleStringProperty("");
        this.date = new SimpleObjectProperty<LocalDate>();
        this.hotelName = new SimpleStringProperty("");
        this.cityName = new SimpleStringProperty("");
        this.countryName = new SimpleStringProperty("");
    }

    public Review(String username, String nationality, int rating, String text, LocalDate date, String hotelName, String cityName, String countryName) {
        this.username = new SimpleStringProperty(username);
        this.nationality = new SimpleStringProperty(nationality);
        this.rating = new SimpleIntegerProperty(rating);
        this.text = new SimpleStringProperty(text);
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.hotelName = new SimpleStringProperty(hotelName);
        this.cityName = new SimpleStringProperty(cityName);
        this.countryName = new SimpleStringProperty(countryName);
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

    public String getNationality() {
        return nationality.get();
    }

    public SimpleStringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public int getRating() {
        return rating.get();
    }

    public SimpleIntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public String getText() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getHotelName() {
        return hotelName.get();
    }

    public SimpleStringProperty hotelNameProperty() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName.set(hotelName);
    }

    public String getCityName() {
        return cityName.get();
    }

    public SimpleStringProperty cityNameProperty() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName.set(cityName);
    }

    public String getCountryName() {
        return countryName.get();
    }

    public SimpleStringProperty countryNameProperty() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName.set(countryName);
    }
}
