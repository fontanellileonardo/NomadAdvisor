import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class CityInterface {
	
	ObservableList<String> levels = 
            FXCollections.observableArrayList (
                " ","1","2","3","4","5");
	ObservableList<String> tempRange = 
            FXCollections.observableArrayList (
                " ","<0","0-15","15-25",">25");
	
    @FXML
    private AnchorPane primaryPane;

    @FXML
    private Button logOutButton;

    @FXML
    private Button profileButton;

    @FXML
    private TextField cityText;

    @FXML
    private TableView<?> cityTable;

    @FXML
    private TableColumn<?, ?> countryColumn;

    @FXML
    private TableColumn<?, ?> cityColumn;

    @FXML
    private TableColumn<?, ?> characteristicsColumn;
    
    @FXML
    private Label cityTitle;

    @FXML
    private Text cityLabel;

    @FXML
    private Button cityNameButton;

    @FXML
    private Button hotelButton;

    @FXML
    private VBox preferencesBox;

    @FXML
    private GridPane preferencesPane;

    @FXML
    private Text temperatureLabel;

    @FXML
    private ComboBox<String> temperatureBox;

    @FXML
    private Text qualityLifeLabel;

    @FXML
    private ComboBox<String> qualityLifeBox;

    @FXML
    private Text englishLabel;

    @FXML
    private ComboBox<String> englishBox;

    @FXML
    private Text friendlyLabel;

    @FXML
    private ComboBox<String> friendlyBox;

    @FXML
    private Text nigthlifeLabel;

    @FXML
    private ComboBox<String> nigthlifeBox;

    @FXML
    private Text costLabel;

    @FXML
    private TextField costField;

    @FXML
    private Text healthLabel;

    @FXML
    private ComboBox<String> helathBox;

    @FXML
    private Text safetyLabel;

    @FXML
    private ComboBox<String> safetyBox;

    @FXML
    private Text walkabilityLabel;

    @FXML
    private ComboBox<String> walkabilityBox;

    @FXML
    private Text airQualityLabel;

    @FXML
    private ComboBox<String> airQualityBox;

    @FXML
    private Text wifiLabel;

    @FXML
    private ComboBox<String> wifiBox;

    @FXML
    private Text prefTitle;

    @FXML
    private Button cityPrefButton;
    
    @FXML
    private void initialize() {
    	// Combobox initialization
    	ComboBox[] cb = {wifiBox, airQualityBox, walkabilityBox, safetyBox, helathBox, 
    			nigthlifeBox, friendlyBox, englishBox, qualityLifeBox, };
    	setLevels(cb);
    	temperatureBox.getItems().removeAll(temperatureBox.getItems());
    	temperatureBox.getItems().addAll(tempRange);
    }
    
    private void setLevels(ComboBox[] cb) {
    	for(int i = 0; i<cb.length; i++) {
    		cb[i].getItems().removeAll(cb[i].getItems());
    		cb[i].getItems().addAll(levels);
    	}
    }
    
    @FXML
    void cityNameButton(ActionEvent event) {
    	
    }

    @FXML
    void cityPrefButton(ActionEvent event) {

    }
    
    @FXML
    void profileButton(ActionEvent event) {

    }

    @FXML
    void hotelButton(ActionEvent event) {

    }

    @FXML
    void logOutButton(ActionEvent event) {

    }
    
    class City {
    	SimpleStringProperty countryProperty;
    	SimpleStringProperty nameProperty;
    	SimpleStringProperty nightLifeProperty;
    	
    	public City(String country, String name, String nigthlife) {
    		this.countryProperty = new SimpleStringProperty(country); 
    		this.nameProperty = new SimpleStringProperty(name);
    		this.nightLifeProperty = new SimpleStringProperty(nigthlife);
    	}
    	
    	public String getCountryProperty() {
    		return countryProperty.get();
    	}
    	
    	public String getNameProperty() {
    		return nameProperty.get() ;
    	}
    	
    	public String getNightLifeProperty() {
    		return nightLifeProperty.get() ;
    	}
    }

}
