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
    void comeBack(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void savePreferences(ActionEvent event) {
    	List<String> preferences = this.getPreferences();
    	for(int i = 0; i < preferences.size(); i++)
    		System.out.println(preferences.get(i));
    }
    
    public void setUsernameLabel(String username) {
    	usernameLabel.setText(username);
    }
    
    public void setNameLabel(String username) {
    	nameLabel.setText("Name: " + username);
    }
    
    public void setSurnameLabel(String username) {
    	surnameLabel.setText("Surname: " + username);
    }
    
    public void setEmailLabel(String username) {
    	emailLabel.setText("Email: " + username);
    }
    
    public void setPreferences(List<String> preferences) {
    	if(preferences.contains(tempCheckBox.getText())) tempCheckBox.setSelected(true);
    	if(preferences.contains(airCheckBox.getText())) airCheckBox.setSelected(true);
    	if(preferences.contains(qualityLifeCheckBox.getText())) qualityLifeCheckBox.setSelected(true);
    	if(preferences.contains(foreignersCheckBox.getText())) foreignersCheckBox.setSelected(true);
    	if(preferences.contains(healthcareCheckBox.getText())) healthcareCheckBox.setSelected(true);
    	if(preferences.contains(nightlifeCheckBox.getText())) nightlifeCheckBox.setSelected(true);
    	if(preferences.contains(costCheckBox.getText())) costCheckBox.setSelected(true);
    	if(preferences.contains(safetyCheckBox.getText())) safetyCheckBox.setSelected(true);
    	if(preferences.contains(walkabilityCheckBox.getText())) walkabilityCheckBox.setSelected(true);
    	if(preferences.contains(wifiCheckBox.getText())) wifiCheckBox.setSelected(true);
    	if(preferences.contains(englishCheckBox.getText())) englishCheckBox.setSelected(true);
    }
    
    public List<String> getPreferences() {
    	List<String> preferences = new ArrayList<>();
    	if(tempCheckBox.isSelected()) preferences.add(tempCheckBox.getText());
    	if(airCheckBox.isSelected()) preferences.add(airCheckBox.getText());
    	if(qualityLifeCheckBox.isSelected()) preferences.add(qualityLifeCheckBox.getText());
    	if(foreignersCheckBox.isSelected()) preferences.add(foreignersCheckBox.getText());
    	if(healthcareCheckBox.isSelected()) preferences.add(healthcareCheckBox.getText());
    	if(nightlifeCheckBox.isSelected()) preferences.add(nightlifeCheckBox.getText());
    	if(costCheckBox.isSelected()) preferences.add(costCheckBox.getText());
    	if(safetyCheckBox.isSelected()) preferences.add(safetyCheckBox.getText());
    	if(walkabilityCheckBox.isSelected()) preferences.add(walkabilityCheckBox.getText());
    	if(wifiCheckBox.isSelected()) preferences.add(wifiCheckBox.getText());
    	if(englishCheckBox.isSelected()) preferences.add(englishCheckBox.getText());
    	return preferences;
    }

}
