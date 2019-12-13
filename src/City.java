import java.util.HashMap;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class City {

    private final SimpleObjectProperty<HashMap<String,Integer>> characteristics;
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty countryName;
    
    public City() {
    	characteristics = new SimpleObjectProperty<HashMap<String,Integer>>();
        cityName = new SimpleStringProperty("");
        countryName = new SimpleStringProperty("");
    }

    public City(HashMap<String,Integer> characteristics,String cityName, String countryName) {
        this.characteristics = new SimpleObjectProperty<HashMap<String,Integer>>(characteristics);
        this.cityName = new SimpleStringProperty(cityName);
        this.countryName = new SimpleStringProperty(countryName);
    }

    public String getCityName() {
        return cityName.get();
    }
    
    public HashMap<String,Integer> getCharacteristics() {
    	return characteristics.get();
    }


    public String getCountryName() {
        return countryName.get();
    }

    public void setCityName(String c) {
        cityName.set(c);
    }
    
    public void setCharacteristics(HashMap<String,Integer> c) {
    	characteristics.set(c);
    }

    public void setCountryName(String c) {
        countryName.set(c);
    }

}
