import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Hotel {

    private final SimpleStringProperty hotelName;
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty countryName;
    private final SimpleIntegerProperty avgScore;
    private final SimpleStringProperty address;
    private final SimpleStringProperty website;

    public Hotel() {
        this.hotelName = new SimpleStringProperty("");
        this.cityName = new SimpleStringProperty("");
        this.countryName = new SimpleStringProperty("");
        this.avgScore = new SimpleIntegerProperty(0);
        this.address = new SimpleStringProperty("");
        this.website = new SimpleStringProperty("");
    }

    public Hotel(String hotelName, String cityName, String countryName, int avgScore, String address, String website) {
        this.hotelName = new SimpleStringProperty(hotelName);
        this.cityName = new SimpleStringProperty(cityName);
        this.countryName = new SimpleStringProperty(countryName);
        this.avgScore = new SimpleIntegerProperty(avgScore);
        this.address = new SimpleStringProperty(address);
        this.website = new SimpleStringProperty(website);
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

    public int getAvgScore() {
        return avgScore.get();
    }

    public SimpleIntegerProperty avgScoreProperty() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
        this.avgScore.set(avgScore);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getWebsite() {
        return website.get();
    }

    public SimpleStringProperty websiteProperty() {
        return website;
    }

    public void setWebsite(String website) {
        this.website.set(website);
    }
}
