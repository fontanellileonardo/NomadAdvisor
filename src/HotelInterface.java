import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.Date;
import java.util.List;

public class HotelInterface {
    private NomadAdvisor nomadAdvisor;
    private ObservableList<Hotel> hotelList;
    private ObservableList<Review> reviewList;

    @FXML
    private AnchorPane primaryPane;

    @FXML
    private Button logoutButton;

    @FXML
    private Button personalAreaButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Hotel> hotelTable;

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

    @FXML
    private TableColumn<Hotel, String> hotelNameColumn;

    @FXML
    private TableColumn<Hotel, String> addressNameColumn;

    @FXML
    private TableColumn<Hotel, Integer> avgColumn;

    @FXML
    private TableColumn<Hotel, String> websiteColumn;

    @FXML
    private TableView<Review> reviewTable;

    @FXML
    private TableColumn<Review, String> usernameColumn;

    @FXML
    private TableColumn<Review, Integer> ratingColumn;

    @FXML
    private TableColumn<Review, Date> dateColumn;

    @FXML
    private TableColumn<Review, String> textColumn;
    
    private ObservableList<String>scores = FXCollections.observableArrayList("1","2","3","4","5");

    @FXML
    void addReviewSelected(ActionEvent event) {
        Hotel selectedHotel = hotelTable.getSelectionModel().getSelectedItem();
        String comment = addReviewField.getText();
        String mark = chooseMarkBox.getValue();
        System.out.print(comment);
        if(selectedHotel != null){
            comment = addReviewField.getText();
            System.out.println(comment);
        }

    }

    @FXML
    void backSelected(ActionEvent event) {
        nomadAdvisor.changeScene("cityInterface");
    }

    @FXML
    void logoutSelected(ActionEvent event) {
        nomadAdvisor.changeScene("loginInterface");
    }

    @FXML
    void personalAreaSelected(ActionEvent event) {
        nomadAdvisor.changeScene("personalArea");
    }

    public void listHotelsUpdate(List<Hotel> hotels){
        hotelList.clear();
        hotelList.addAll(hotels);
    }

    public static final Callback<TableColumn<Review,String>, TableCell<Review,String>> WRAPPING_CELL_FACTORY =
            new Callback<TableColumn<Review,String>, TableCell<Review,String>>() {

                @Override public TableCell<Review,String> call(TableColumn<Review,String> param) {
                    TableCell<Review,String> tableCell = new TableCell<Review,String>() {
                        @Override protected void updateItem(String item, boolean empty) {
                            if (item == getItem()) return;

                            super.updateItem(item, empty);

                            if (item == null) {
                                super.setText(null);
                                super.setGraphic(null);
                            } else {
                                super.setText(null);
                                Label l = new Label(item);
                                l.setWrapText(true);
                                VBox box = new VBox(l);
                                l.heightProperty().addListener((observable,oldValue,newValue)-> {
                                    box.setPrefHeight(newValue.doubleValue()+7);
                                    Platform.runLater(()->this.getTableRow().requestLayout());
                                });
                                super.setGraphic(box);
                            }
                        }
                    };
                    return tableCell;
                }
            };

    public void initialize(String city, String country, NomadAdvisor m) {
        nomadAdvisor = m;

        chooseMarkBox.getItems().addAll(scores);

        hotelTableTitle.setText(city + " hotels");

        hotelList = FXCollections.observableArrayList();
        Hotel h = new Hotel("Hotel Palazzaccio", "Cecina", "Italia", 5, "Via Aurelia 34", "www.palazzaccio.it");
        hotelList.add(h);

        hotelNameColumn.setCellValueFactory(new PropertyValueFactory("hotelName"));
        addressNameColumn.setCellValueFactory(new PropertyValueFactory("address"));
        avgColumn.setCellValueFactory(new PropertyValueFactory("avgScore"));
        websiteColumn.setCellValueFactory(new PropertyValueFactory("website"));

        reviewList = FXCollections.observableArrayList();
        Review r = new Review("Fonta", "Italy", 5, "Ottimo!dgsuaifishduhiuogahshflusarhagòuhrguhstpighjo09df8gshrdfpghdroghidfoghiodfghiodfhiudsfioghjfiodjgioòdfjgpàiofjàpfdjàgjdfà9sgjhà90rfghjs9erjhgrejsgà", new Date(2019,9, 12), "Hotel Palazzaccio" , "Cecina", "Italy");
        reviewList.add(r);

        usernameColumn.setCellValueFactory(new PropertyValueFactory("username"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        textColumn.setCellValueFactory(new PropertyValueFactory("text"));

        reviewTable.setItems(reviewList);
        hotelTable.setItems(hotelList);

        textColumn.setCellFactory(WRAPPING_CELL_FACTORY);

    }

}