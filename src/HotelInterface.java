import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class HotelInterface {

    @FXML
    private AnchorPane primaryPane;

    @FXML
    private Button logoutButton;

    @FXML
    private Button personalAreaButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> hotelTable;

    @FXML
    private TableView<?> reviewTable;

    @FXML
    private TextField addReviewField;

    @FXML
    private ComboBox<String> chooseMarkBox;

    @FXML
    private Button addReviewButton;

    @FXML
    private Text hotelTableTitle;
    
    @FXML
    private ImageView logoImage;

    @FXML
    private Text reviewTableTitle;
    
    private ObservableList<String>scores = FXCollections.observableArrayList("1","2","3","4","5");

    @FXML
    void addReviewSelected(ActionEvent event) {

    }

    @FXML
    void backSelected(ActionEvent event) {

    }

    @FXML
    void logoutSelected(ActionEvent event) {

    }

    @FXML
    void personalAreaSelected(ActionEvent event) {

    }
    
    @FXML
    private void initialize() {
    	chooseMarkBox.getItems().addAll(scores);
    }

}
