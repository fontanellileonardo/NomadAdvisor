import java.util.List;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class employeeInterface {
	
	City selectedCity;

    @FXML private AnchorPane backgroundPane;
    @FXML private ImageView logo;
    @FXML private Pane bodyPane;
    @FXML private ComboBox<String> chooseView;
    private ObservableList<String> viewList = 
    		FXCollections.observableArrayList(
    		"Customer View","City view");
    @FXML private Button statButton; 
    
    @FXML private VBox customerPanel;
    @FXML private Label customerTitle;
    //Customer Table Section
    private ObservableList<Customer> customerList = 
    		FXCollections.observableArrayList();
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> customerNameCol;
    @FXML private TableColumn<Customer, String> surnameCol;
    @FXML private TableColumn<Customer, String> usernameCol;
    @FXML private TableColumn<Customer, String> emailCol;
    
    @FXML private VBox cityPanel;
    @FXML private Label cityTitle;
    //City Table Section
    private ObservableList<City> cityList = 
    		FXCollections.observableArrayList();
    @FXML private TableView<City> cityTable;
    @FXML private TableColumn<City, String> cityNameCol;
    @FXML private TableColumn<City, String> cityCountryCol;
    @FXML private TableColumn<City, String> cityCharCol;
    
    @FXML private Button newCityButton;
    @FXML private Button deleteCityButton;
    
    @FXML private VBox hotelPanel;
    @FXML private Label hotelTitle;
    //Hotel Table Section
    private ObservableList<Hotel> hotelList = FXCollections.observableArrayList();
    @FXML private TableView<Hotel> hotelTable;
    @FXML private TableColumn<Hotel, String> hotelNameCol;
    @FXML private TableColumn<Hotel, String> hotelAddressCol;
    @FXML private TableColumn<Hotel, String> avgColumn;
    @FXML private TableColumn<Hotel, String> webColumn;
    
    @FXML private Button newHotelButton;
    @FXML private Button deleteHotelButton;

    @FXML private Text logMsg;
    @FXML private Button logoutButton;
    @FXML private Text title;
    
    @FXML
    private void initialize() {
    	chooseView.setItems(viewList);
    	initializeCustomerTable();
    	initializeCityTable();
    	initializeHotelTable();
    }

    private void initializeCustomerTable() {
    	 customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
    	 surnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
    	 usernameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));;
    	 emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
    
    	 customerTable.setItems(customerList);
    }
    
    private void initializeCityTable() {
    	
    	cityNameCol.setCellValueFactory(new PropertyValueFactory<City, String>("cityName")); 
    	cityCountryCol.setCellValueFactory(new PropertyValueFactory<City, String>("countryName")); 
    	cityCharCol.setCellValueFactory(new PropertyValueFactory<City, String>("attributes")); 

    	cityTable.setItems(cityList);
    	
    	cityTable.getSelectionModel().selectedIndexProperty().addListener((num) ->
        {           
            if(cityTable.getSelectionModel().getSelectedItem() == null) {
               cityTable.getSelectionModel().clearSelection();

            }
            else {

                selectedCity = cityTable.getSelectionModel().getSelectedItem();
                System.out.println("I selected: "+selectedCity.getCityName());
                
                //show hotels related to the selectedCity or delete selected city
                //call to the listUpdate with the choosen city for the hotelTable
           }
        });
    }
    
    private void initializeHotelTable() {
    	hotelNameCol.setCellValueFactory(new PropertyValueFactory<Hotel, String>("hotelName"));
        hotelAddressCol.setCellValueFactory(new PropertyValueFactory<Hotel, String>("address"));;
        avgColumn.setCellValueFactory(new PropertyValueFactory<Hotel, String>("avgScore"));
        webColumn.setCellValueFactory(new PropertyValueFactory<Hotel, String>("website"));
    
        hotelTable.setItems(hotelList);
    }
    
    public void cityListUpdate(List<City> cities){
        cityList.clear();
        cityList.addAll(cities);
    }
    
    public void hotelListUpdate(List<Hotel> hotels){
        hotelList.clear();
        hotelList.addAll(hotels);
    }
    
    
    @FXML
    void addCity(ActionEvent event) {
    	System.out.println("New City added: "+selectedCity.getCityName());
    	
    }

    @FXML
    void addHotel(ActionEvent event) {

    }

    @FXML
    void changeView(ActionEvent event) {
    	ObservableList paneChildren = bodyPane.getChildren();
    	if(chooseView.getValue() == "Customer View") {
    		//retrieve the node related to the table to hide and hide it
    		((VBox)paneChildren.get(paneChildren.indexOf(cityPanel))).setVisible(false);
    		((VBox)paneChildren.get(paneChildren.indexOf(hotelPanel))).setVisible(false);
    		
    		//show the right table
    		((VBox)paneChildren.get(paneChildren.indexOf(customerPanel))).setVisible(true);

    	}
    	else {
    		//retrieve the node related to the table to hide and hide it
    		((VBox)paneChildren.get(paneChildren.indexOf(customerPanel))).setVisible(false);
    		
    		//show the right table
    		((VBox)paneChildren.get(paneChildren.indexOf(cityPanel))).setVisible(true);
    		((VBox)paneChildren.get(paneChildren.indexOf(hotelPanel))).setVisible(true);	
    	}
    		
    		
    }

    @FXML
    void deleteCity(ActionEvent event) {

    }

    @FXML
    void deleteHotel(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void showStatistics(ActionEvent event) {

    }

}
