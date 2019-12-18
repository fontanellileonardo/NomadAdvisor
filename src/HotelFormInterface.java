import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HotelFormInterface {

    @FXML
    private Label titleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label websiteLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField websiteTextField;

    @FXML
    private Button addButton;

    @FXML
    private Label outcomeLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label cityNameLabel;

    @FXML
    private Label countryNameLabel;

    @FXML
    // Gets and checks the fields inserted from the customer and calls the NomadHandler to create the new hotel
    void addHotel(ActionEvent event) {
    	outcomeLabel.setText("");
    	String name = nameTextField.getText();
    	String address = addressTextField.getText();
    	if(name.equals("") || address.equals("")) {
    		outcomeLabel.setText("You must fill both the Name and Address fields");
    		return;
    	}
    	String website = websiteTextField.getText().equals("") ? null : websiteTextField.getText();
    	outcomeLabel.setText(NomadHandler.createHotel(name, cityNameLabel.getText(), countryNameLabel.getText(), address, website));
    }
    
    // Initialize the interface fields
    public void initInterface(String city, String country) {
    	clearAll();
    	cityNameLabel.setText(city);
    	countryNameLabel.setText(country);
    }
    
    // Clears all the interface fields
    private void clearAll() {
    	outcomeLabel.setText("");
    	nameTextField.setText("");
    	addressTextField.setText("");
    	websiteTextField.setText("");
    }

}
