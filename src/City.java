import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class City {

    private final SimpleIntegerProperty cost;
    private final SimpleIntegerProperty temperature;
    private final SimpleIntegerProperty airQuality;
    private final SimpleIntegerProperty safety;
    private final SimpleIntegerProperty qualityLife;
    private final SimpleIntegerProperty walkability;
    private final SimpleIntegerProperty healthcare;
    private final SimpleIntegerProperty nightlife;
    private final SimpleIntegerProperty wifi;
    private final SimpleIntegerProperty foreignersFriendly;
    private final SimpleIntegerProperty english;
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty countryName;
    
    public City() {
        cost = new SimpleIntegerProperty(0);
        temperature = new SimpleIntegerProperty(0);
        airQuality = new SimpleIntegerProperty(0);
        safety = new SimpleIntegerProperty(0);
        qualityLife = new SimpleIntegerProperty(0);
        walkability = new SimpleIntegerProperty(0);
        healthcare = new SimpleIntegerProperty(0);
        nightlife = new SimpleIntegerProperty(0);
        wifi = new SimpleIntegerProperty(0);
        foreignersFriendly = new SimpleIntegerProperty(0);
        english = new SimpleIntegerProperty(0);
        cityName = new SimpleStringProperty("");
        countryName = new SimpleStringProperty("");
    }

    public City(int cost, int temperature, int airQuality, int safety, int qualityLife, int walkability, int healthcare, int nightlife, int wifi, int foreignersFriendly, int english, String cityName, String countryName) {
        this.cost = new SimpleIntegerProperty(cost);
        this.temperature = new SimpleIntegerProperty(temperature);
        this.airQuality = new SimpleIntegerProperty(airQuality);
        this.safety = new SimpleIntegerProperty(safety);
        this.qualityLife = new SimpleIntegerProperty(qualityLife);
        this.walkability = new SimpleIntegerProperty(walkability);
        this.healthcare = new SimpleIntegerProperty(healthcare);
        this.nightlife = new SimpleIntegerProperty(nightlife);
        this.wifi = new SimpleIntegerProperty(wifi);
        this.foreignersFriendly = new SimpleIntegerProperty(foreignersFriendly);
        this.english = new SimpleIntegerProperty(english);
        this.cityName = new SimpleStringProperty(cityName);
        this.countryName = new SimpleStringProperty(countryName);
    }

    public int getCost() {
        return cost.get();
    }

    public int getTemperature() {
        return temperature.get();
    }

    public int getAirQuality() {
        return airQuality.get();
    }

    public int getSafety() {
        return safety.get();
    }

    public int getQualityLife() {
        return qualityLife.get();
    }

    public int getWalkability() {
        return walkability.get();
    }

    public int getHealthcare() {
        return healthcare.get();
    }

    public int getNightlife() {
        return nightlife.get();
    }

    public int getWifi() {
        return wifi.get();
    }

    public int getForeignersFriendly() {
        return foreignersFriendly.get();
    }

    public int getEnglish() {
        return english.get();
    }

    public String getCityName() {
        return cityName.get();
    }

    public String getCountryName() {
        return countryName.get();
    }
    
    public void setCost(int c) {
        cost.set(c);
    }
    
    public void setTemperature(int t) {
        temperature.set(t);
    }

    public void setAirQuality(int a) {
        airQuality.set(a);
    }

    public void setSafety(int s) {
        safety.set(s);
    }

    public void setQualityLife(int q) {
        qualityLife.set(q);
    }

    public void setWalkability(int w) {
        walkability.set(w);
    }

    public void setHealthcare(int h) {
        healthcare.set(h);
    }

    public void setNightlife(int n) {
        nightlife.set(n);
    }

    public void setWifi(int w) {
        wifi.set(w);
    }

    public void setForeignersFriendly(int f) {
        foreignersFriendly.set(f);
    }

    public void setEnglish(int e) {
        english.set(e);
    }

    public void setCityName(String c) {
        cityName.set(c);
    }

    public void setCountryName(String c) {
        countryName.set(c);
    }
    
    


}
