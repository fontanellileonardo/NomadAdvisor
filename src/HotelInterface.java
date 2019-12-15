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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class HotelInterface {
    private NomadAdvisor nomadAdvisor;
    private ObservableList<Hotel> hotelList;
    private ObservableList<Review> reviewList;
    private Customer loggedCustomer;
    private City city;
    private Hotel selectedHotel;

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
    private Text userMsg;

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
    private Text reviewMessage;

    @FXML
    private TableColumn<Review, String> usernameColumn;

    @FXML
    private TableColumn<Review, Integer> ratingColumn;

    @FXML
    private TableColumn<Review, Date> dateColumn;

    @FXML
    private TableColumn<Review, String> textColumn;
    
    private ObservableList<String> scores = FXCollections.observableArrayList("1","2","3","4","5");

    @FXML
    void addReviewSelected(ActionEvent event) {
        Hotel selectedHotel = hotelTable.getSelectionModel().getSelectedItem();
        String comment = addReviewField.getText();
        String mark = chooseMarkBox.getValue();
        if(selectedHotel != null && mark != null && !comment.equals("") && !mark.equals("Rating")){
            Review newReview = new Review(loggedCustomer.getUsername(), loggedCustomer.getNationality(), Integer.parseInt(mark), comment, LocalDate.now(), selectedHotel.getHotelName(), selectedHotel.getCityName(), selectedHotel.getCountryName());
            if(!NomadHandler.addReview(newReview)){
                //ERROR SITUATION
                userMsg.setText("Oops! Something went wrong!");
            }
            else{
                //CORRECT INSERTION
                userMsg.setText("Review succesfully inserted");
                addReviewField.clear();
                chooseMarkBox.setValue("Rating");
                listReviewUpdate(NomadHandler.getReviews(selectedHotel));
            }
        }
        else{
            userMsg.setText("Review insertion failed: missing fields ");
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

    public void listReviewUpdate(List<Review> reviews){
        reviewList.clear();
        reviewList.addAll(reviews);
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

    public void initialize(City city, Customer loggedCustomer, NomadAdvisor nomadAdvisor) {
        this.nomadAdvisor = nomadAdvisor;
        this.loggedCustomer = loggedCustomer;
        this.city = city;
        userMsg.setText("");

        chooseMarkBox.getItems().addAll(scores);

        hotelTableTitle.setText(city.getCityName() + " hotels");

        hotelList = FXCollections.observableArrayList();
        listHotelsUpdate(NomadHandler.getHotels(city));

        hotelNameColumn.setCellValueFactory(new PropertyValueFactory("hotelName"));
        addressNameColumn.setCellValueFactory(new PropertyValueFactory("address"));
        avgColumn.setCellValueFactory(new PropertyValueFactory("avgScore"));
        websiteColumn.setCellValueFactory(new PropertyValueFactory("website"));

        reviewList = FXCollections.observableArrayList();

        usernameColumn.setCellValueFactory(new PropertyValueFactory("username"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        textColumn.setCellValueFactory(new PropertyValueFactory("text"));

        selectedHotel = new Hotel();
        hotelTable.getSelectionModel().selectedIndexProperty().addListener((num) ->
        {
            if(hotelTable.getSelectionModel().getSelectedItem() == null) {
                hotelTable.getSelectionModel().clearSelection();

            }
            else {
                selectedHotel = hotelTable.getSelectionModel().getSelectedItem();
                System.out.println("Selected: "+ selectedHotel.getHotelName());
                listReviewUpdate(NomadHandler.getReviews(selectedHotel));
                reviewMessage.setText("Add a new review for " + selectedHotel.getHotelName() + ":");
            }
        });

        reviewTable.setItems(reviewList);
        hotelTable.setItems(hotelList);

        textColumn.setCellFactory(WRAPPING_CELL_FACTORY);

    }

}