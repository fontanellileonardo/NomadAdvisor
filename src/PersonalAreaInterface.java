import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;

public class PersonalAreaInterface {

    @FXML
    private Button logoutButton;

    @FXML
    private Button backButton;

    @FXML
    private CheckBox tempCheckBox;

    @FXML
    private Label nameLabel;

    @FXML
    private Label titleLebel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Separator upperSeparator;

    @FXML
    private Label preferencesLabel;

    @FXML
    private Separator lowerSeparator;

    @FXML
    private CheckBox airCheckBox;

    @FXML
    private CheckBox qualityLifeCheckBox;

    @FXML
    private CheckBox foreignersCheckBox;

    @FXML
    private CheckBox healthcareCheckBox;

    @FXML
    private CheckBox nightlifeCheckBox;

    @FXML
    private CheckBox costCheckBox;

    @FXML
    private CheckBox safetyCheckBox;

    @FXML
    private CheckBox walkabilityCheckBox;

    @FXML
    private CheckBox wifiCheckBox;

    @FXML
    private CheckBox englishCheckBox;

    @FXML
    private Button saveButton;

    @FXML
    private Label emailLabel;
    
    @FXML
    private Label errorLabel;
    
    private NomadAdvisor nomadAdvisor;

    @FXML
    void comeBack(ActionEvent event) {
    	errorLabel.setText("");
    	System.out.println("Add city interface in the code!");
    	//nomadAdvisor.changeScene("cityInterface");
    }

    @FXML
    void logout(ActionEvent event) {
    	errorLabel.setText("");
    	nomadAdvisor.changeScene("loginInterface");
    }

    @FXML
    void savePreferences(ActionEvent event) {
    	List<String> preferences = this.getPreferences();
    	Customer customer = (Customer) nomadAdvisor.getLoggedUser();
    	errorLabel.setText(nomadAdvisor.getNomadHandler().updatePreferences(customer, preferences));
    }
    
    private void setUsernameLabel(String username) {
    	usernameLabel.setText(username);
    }
    
    private void setNameLabel(String username) {
    	nameLabel.setText("Name: " + username);
    }
    
    private void setSurnameLabel(String username) {
    	surnameLabel.setText("Surname: " + username);
    }
    
    private void setEmailLabel(String username) {
    	emailLabel.setText("Email: " + username);
    }
    
    private void setPreferences(List<String> preferences) {
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE))) tempCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.AIR_QUALITY))) airCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.QUALITY_LIFE))) qualityLifeCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.FOREIGNERS))) foreignersCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.HEALTHCARE))) healthcareCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.NIGHTLIFE))) nightlifeCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.COST))) costCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.SAFETY))) safetyCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.WALKABILITY))) walkabilityCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.WIFI))) wifiCheckBox.setSelected(true);
    	if(preferences.contains(Utils.cityAttributes.get(Utils.cityNames.ENGLISH))) englishCheckBox.setSelected(true);
    }
    
    private List<String> getPreferences() {
    	List<String> preferences = new ArrayList<>();
    	if(tempCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE));
    	if(airCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.AIR_QUALITY));
    	if(qualityLifeCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.QUALITY_LIFE));
    	if(foreignersCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.FOREIGNERS));
    	if(healthcareCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.HEALTHCARE));
    	if(nightlifeCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.NIGHTLIFE));
    	if(costCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.COST));
    	if(safetyCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.SAFETY));
    	if(walkabilityCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.WALKABILITY));
    	if(wifiCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.WIFI));
    	if(englishCheckBox.isSelected()) preferences.add(Utils.cityAttributes.get(Utils.cityNames.ENGLISH));
    	return preferences;
    }
    
    public void initialize(Customer customer, NomadAdvisor nomadAdvisor) {
    	if(customer != null) {
	    	this.setUsernameLabel(customer.getUsername());
	    	this.setNameLabel(customer.getName());
	    	this.setSurnameLabel(customer.getSurname());
	    	this.setEmailLabel(customer.getEmail());
	    	this.setPreferences(customer.getPreferences());
    	}
    	this.nomadAdvisor = nomadAdvisor;
    }

}
