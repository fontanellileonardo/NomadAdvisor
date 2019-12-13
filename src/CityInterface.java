import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class CityInterface {
	
    @FXML private AnchorPane primaryPane;
    @FXML private Label cityTitle;
    @FXML private Button logOutButton;
    @FXML private Button profileButton;

    // Search panel by city name
    @FXML private Text cityLabel;
    @FXML private TextField cityText;
    @FXML private Button cityNameButton;
    
    // Search panel by preferences
	ObservableList<String> levels = 
            FXCollections.observableArrayList (
                " ","1","2","3","4","5");
	ObservableList<String> tempRange = 
            FXCollections.observableArrayList (
                " ","<0","0-15","15-25",">25");
    @FXML private VBox preferencesBox;
    @FXML private GridPane preferencesPane;
    @FXML private Text prefTitle;
    @FXML private Text temperatureLabel;
    @FXML private ComboBox<String> temperatureBox;
    @FXML private Text qualityLifeLabel;
    @FXML private ComboBox<String> qualityLifeBox;
    @FXML private Text englishLabel;
    @FXML private ComboBox<String> englishBox;
    @FXML private Text friendlyLabel;
    @FXML private ComboBox<String> friendlyBox;
    @FXML private Text nigthlifeLabel;
    @FXML private ComboBox<String> nigthlifeBox;
    @FXML private Text costLabel;
    @FXML private TextField costField;
    @FXML private Text healthLabel;
    @FXML private ComboBox<String> helathBox;
    @FXML private Text safetyLabel;
    @FXML private ComboBox<String> safetyBox;
    @FXML private Text walkabilityLabel;
    @FXML private ComboBox<String> walkabilityBox;
    @FXML private Text airQualityLabel;
    @FXML private ComboBox<String> airQualityBox;
    @FXML private Text wifiLabel;
    @FXML private ComboBox<String> wifiBox;
    @FXML private Button cityPrefButton;
    
    // City Table
    private ObservableList<City> cityList = 
    		FXCollections.observableArrayList();
    @FXML private TableView<City> cityTable;
    @FXML private TableColumn<City, String> countryColumn;
    @FXML private TableColumn<City, String> cityColumn;
    @FXML private TableColumn<City, String> characteristicsColumn;
    @FXML private Button hotelButton;
    
    @FXML private void initialize() {
    	initializeCityTable();
    	initializeComboBox();
    }
     
    // City Table initialization
    private void initializeCityTable() {
    	cityColumn.setCellValueFactory(new PropertyValueFactory<City, String>("cityName")); 
    	countryColumn.setCellValueFactory(new PropertyValueFactory<City, String>("countryName")); 
    	characteristicsColumn.setCellValueFactory(new PropertyValueFactory<City, String>("characteristics")); 
    	characteristicsColumn.setCellFactory(Utils.WRAPPING_CELL_FACTORY);

    	cityTable.setItems(cityList);
    }
    
    public void cityListUpdate(List<City> cities) {
    	cityList.clear();
    	cityList.addAll(cities);
    }
    
    // Combobox initialization
    private void initializeComboBox() {
    	ComboBox[] cb = {wifiBox, airQualityBox, walkabilityBox, safetyBox, helathBox, 
    			nigthlifeBox, friendlyBox, englishBox, qualityLifeBox, };
    	setLevels(cb);
    	temperatureBox.getItems().removeAll(temperatureBox.getItems());
    	temperatureBox.getItems().addAll(tempRange);
    }
    
    // Set the levels of the comboboxes
    private void setLevels(ComboBox[] cb) {
    	for(int i = 0; i<cb.length; i++) {
    		cb[i].getItems().removeAll(cb[i].getItems());
    		cb[i].getItems().addAll(levels);
    	}
    }
    
    // Search city by Name
    @FXML void cityNameButton(ActionEvent event) {
    	String name = cityText.getText().trim();
    	List<City> cities = NomadHandler.getCity(name);
    	if(cities != null)
    		cityListUpdate(cities);
    }

    // Search city by attributes
    @FXML void cityPrefButton(ActionEvent event) {
    	
    }
    
    // Go to the customer's personal area interface
    @FXML void profileButton(ActionEvent event) {

    }

    // Search the hotels related to the selected city and go to the relative interface
    @FXML void hotelButton(ActionEvent event) {

    }

    // Go back to the Login interface
    @FXML void logOutButton(ActionEvent event) {

    }

}
