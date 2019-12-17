import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CityFormController {

    @FXML
    private VBox cityForm;

    @FXML
    private Label title;

    @FXML
    private Text logMsg;

    @FXML
    private Label cityName;

    @FXML
    private Label countryName;

    @FXML
    private Label charText;

    @FXML
    private TextField cityField;

    @FXML
    private TextField countryField;

    @FXML
    private Label wifiLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label qolLabel;

    @FXML
    private Label engLabel;

    @FXML
    private Label friendlyLabel;

    @FXML
    private Label nightLabel;

    @FXML
    private Label costLabel;

    @FXML
    private Label healtLabel;

    @FXML
    private Label safetyLabel;

    @FXML
    private Label walkLabel;

    @FXML
    private Label airLabel;

    @FXML
    private ComboBox<?> wifiScore;

    @FXML
    private ComboBox<?> costScore;

    @FXML
    private ComboBox<?> tempScore;

    @FXML
    private ComboBox<?> healthScore;

    @FXML
    private ComboBox<?> qolScore;

    @FXML
    private ComboBox<?> safetyScore;

    @FXML
    private ComboBox<?> engScore;

    @FXML
    private ComboBox<?> friendlyScore;

    @FXML
    private ComboBox<?> walkScore;

    @FXML
    private ComboBox<?> nightScore;

    @FXML
    private ComboBox<?> airScore;

    @FXML
    private Button addButton;

    @FXML
    void addCity(ActionEvent event) {

    }

}
