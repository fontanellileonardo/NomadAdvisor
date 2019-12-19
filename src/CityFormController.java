import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CityFormController {

    @FXML private VBox cityForm;
    @FXML private Label title;
    @FXML private Text logMsg;
    @FXML private Label cityName;
    @FXML private Label countryName;
    @FXML private Label charText;
    @FXML private TextField cityField;
    @FXML private TextField countryField;
    @FXML private Label wifiLabel;
    @FXML private Label temperatureLabel;
    @FXML private Label qolLabel;
    @FXML private Label engLabel;
    @FXML private Label friendlyLabel;
    @FXML private Label nightLabel;
    @FXML private Label costLabel;
    @FXML private Label healtLabel;
    @FXML private Label safetyLabel;
    @FXML private Label walkLabel;
    @FXML private Label airLabel;
    @FXML private TextField tempField;
    @FXML private TextField costField;
    @FXML private ComboBox<String> wifiScore;
    @FXML private ComboBox<String> healthScore;
    @FXML private ComboBox<String> qolScore;
    @FXML private ComboBox<String> safetyScore;
    @FXML private ComboBox<String> engScore;
    @FXML private ComboBox<String> friendlyScore;
    @FXML private ComboBox<String> walkScore;
    @FXML private ComboBox<String> nightScore;
    @FXML private ComboBox<String> airScore;
    
    @FXML private Button addButton;
    
    private ObservableList<String> levels = 
            FXCollections.observableArrayList (
                "1","2","3","4","5");
	
	@FXML private void initialize() {
		initializeComboBox();
	}
	
	// Combobox initialization
    private void initializeComboBox() {
    	ComboBox[] cb = {wifiScore, airScore, walkScore, safetyScore, healthScore, 
    			nightScore, friendlyScore, engScore, qolScore};
    	setLevels(cb);
    }
    
    // Set the levels of the comboboxes
    private void setLevels(ComboBox[] cb) {
    	for(int i = 0; i<cb.length; i++) {
    		cb[i].getItems().removeAll(cb[i].getItems());
    		cb[i].getItems().addAll(levels);
    		cb[i].setValue("1");
    	}
    }
    
    @FXML
    void addCity(ActionEvent event) {
    	String name = cityField.getText().trim();
    	String country = countryField.getText().trim();
    	//TODO -> canhe the regex for match only integer
    	if(!(tempField.getText().matches("^-?\\d+\\.?[0-9]*$")) || !(costField.getText().matches("^\\d+\\.?[0-9]*$")))
    		logMsg.setText("Your fields contains unexpected values");
    	
    	else {
    		int temp = Integer.parseInt(tempField.getText().trim());
	    	int cost = Integer.parseInt(costField.getText().trim());
	    	int wifi = Integer.parseInt(wifiScore.getValue().toString());
	    	int air = Integer.parseInt(airScore.getValue().toString());
	    	int walk = Integer.parseInt(walkScore.getValue().toString());
	    	int safety = Integer.parseInt(safetyScore.getValue().toString());
	    	int health = Integer.parseInt(healthScore.getValue().toString());
	    	int night = Integer.parseInt(nightScore.getValue().toString());
	    	int friendly = Integer.parseInt(friendlyScore.getValue().toString());
	    	int eng = Integer.parseInt(engScore.getValue().toString());
	    	int qol = Integer.parseInt(qolScore.getValue().toString());
	    	HashMap<String,Integer> characteristics = new HashMap<String,Integer>();
	    	characteristics.put(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE), temp);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.COST), cost);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.AIR_QUALITY),air);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.SAFETY),safety);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.QUALITY_LIFE), qol);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.WALKABILITY), walk);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.HEALTHCARE), health);		
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.NIGHTLIFE), night);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.WIFI), wifi);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.FOREIGNERS), friendly);
			characteristics.put(Utils.cityAttributes.get(Utils.cityNames.ENGLISH), eng);
		
			City cityAdded = new City(characteristics, name, country);
			logMsg.setText(NomadHandler.createCity(cityAdded));
			if(logMsg.equals("Success!"))
				clean();
    	}	
    }
    
    private void clean() {
    	cityField.clear();
    	countryField.clear();
    	tempField.clear();
    	costField.clear();
    	logMsg.setText("");
    	ComboBox[] cb = {wifiScore, airScore, walkScore, safetyScore, healthScore, 
    			nightScore, friendlyScore, engScore, qolScore};
    	for(int i = 0; i<cb.length; i++)
    		cb[i].setValue("1");	
    }

}
