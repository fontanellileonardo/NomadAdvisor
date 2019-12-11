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
        System.out.println("Come back");
    }

    @FXML
    void logout(ActionEvent event) {
        System.out.println("Logout");
    }

    @FXML
    void savePreferences(ActionEvent event) {
        System.out.println("Save preferences");
    }

}
