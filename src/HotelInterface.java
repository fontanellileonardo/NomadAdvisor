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
    private City city;
    private Hotel selectedHotel;
    @FXML private AnchorPane primaryPane;
    @FXML private Button logoutButton;
    @FXML private Button personalAreaButton;
    @FXML private Button backButton;
    @FXML private ComboBox<String> chooseMarkBox;
    @FXML private Button addReviewButton;
    @FXML private ImageView logoImage;
    @FXML private Text userMsg;
    @FXML private TableView<Hotel> hotelTable;
    @FXML private Text hotelTableTitle;
    @FXML private TableColumn<Hotel, String> hotelNameColumn;
    @FXML private TableColumn<Hotel, String> addressNameColumn;
    @FXML private TableColumn<Hotel, Integer> avgColumn;
    @FXML private TableColumn<Hotel, String> websiteColumn;
    @FXML private Text reviewMessage;
    @FXML private TableView<Review> reviewTable;
    @FXML private Text reviewTableTitle;
    @FXML private TextField addReviewField;
    @FXML private TableColumn<Review, String> usernameColumn;
    @FXML private TableColumn<Review, Integer> ratingColumn;
    @FXML private TableColumn<Review, Date> dateColumn;
    @FXML private TableColumn<Review, String> textColumn;
    private ObservableList<String> scores = FXCollections.observableArrayList("1","2","3","4","5");

    /*
     * This method will be triggered every time the "Add" button of the review will be pressed
     */
    @FXML
    void addReviewSelected(ActionEvent event) {
        Hotel selectedHotel = hotelTable.getSelectionModel().getSelectedItem(); //Retrieve the selected hotel from the table
        String comment = addReviewField.getText(); //Retrieve the text from the text field of the review
        String mark = chooseMarkBox.getValue(); //Retrieve the score selected in the combo-box
        if(selectedHotel != null && mark != null && !mark.equals("Score")){ //If the hotel is not selected, or the score is not selected the review cannot be added
        	Customer loggedCustomer = (Customer) nomadAdvisor.getUser(); //Gets the logged customer data
        	comment = comment.equals("")?null:comment;
            Review newReview = new Review(loggedCustomer.getUsername(), Integer.parseInt(mark), comment, LocalDate.now(), selectedHotel.getHotelName(), selectedHotel.getCityName(), selectedHotel.getCountryName());
            if(!NomadHandler.addReview(newReview)){
                //ERROR SITUATION
                userMsg.setText("Oops! Something went wrong!");
            }
            else{
                //CORRECT INSERTION
                userMsg.setText("Review succesfully inserted");
                addReviewField.clear();
                chooseMarkBox.setValue("Score");
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

    /*
     * This method is used in order to embellish the text field of the reviews in the table
     * Without this, the text will be truncated is the review is too long
     */
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
            
    public void setNomadAdvisor(NomadAdvisor nomadAdvisor) {
    	this.nomadAdvisor = nomadAdvisor;
    }

    /*
     * Initialize the hotel table's columns with the correspondent PropertyValueFactory
     */
    public void initHotelTable(){
        hotelTableTitle.setText(city.getCityName() + " hotels");
        hotelList = FXCollections.observableArrayList();
        listHotelsUpdate(NomadHandler.getHotels(city));
        hotelNameColumn.setCellValueFactory(new PropertyValueFactory("hotelName"));
        addressNameColumn.setCellValueFactory(new PropertyValueFactory("address"));
        avgColumn.setCellValueFactory(new PropertyValueFactory("avgScore"));
        websiteColumn.setCellValueFactory(new PropertyValueFactory("website"));
    }

    /*
     * Initialize the review table's columns with the correspondent PropertyValueFactory
     */
    public void initReviewTable(){
        reviewList = FXCollections.observableArrayList();
        usernameColumn.setCellValueFactory(new PropertyValueFactory("username"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        textColumn.setCellValueFactory(new PropertyValueFactory("text"));
    }

    /*
     * This method sets the listener for the Hotel Interface, every time the user selects a Hotel in the "hotel table",
     * the relative reviews will be shown in the "review table"
     */
    public void setHotelListener(){
        selectedHotel = new Hotel();
        hotelTable.getSelectionModel().selectedIndexProperty().addListener((num) ->
        {
            if(hotelTable.getSelectionModel().getSelectedItem() == null) {
                hotelTable.getSelectionModel().clearSelection();
                userMsg.setText("");
            }
            else {
                userMsg.setText("");
                selectedHotel = hotelTable.getSelectionModel().getSelectedItem();
                System.out.println("Selected: "+ selectedHotel.getHotelName());
                listReviewUpdate(NomadHandler.getReviews(selectedHotel));
                reviewMessage.setText("Add a new review for " + selectedHotel.getHotelName() + ":");
            }
        });
    }

    /*
     * This method initialize the tables
     */
    public void initInterface() {
        userMsg.setText("");
        city = nomadAdvisor.getCity();   // Retrieve the City object from nomadAdvisor
        chooseMarkBox.getItems().addAll(scores); //Populate the combo-box of the review scores
        initHotelTable(); //Initialize the Hotel Table
        initReviewTable();  //Initialize the Review Table
        setHotelListener(); //This method sets the listener for the Hotel selection in the table
        hotelTable.setItems(hotelList);   //Populate the Hotel table
        reviewTable.setItems(reviewList);   //Populate the Review table
        textColumn.setCellFactory(WRAPPING_CELL_FACTORY); //This will embellish the text of the review in the table
    }
}