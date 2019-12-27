import java.io.IOException;
import java.util.List;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EmployeeInterface {
	
	private City selectedCity;
	private Hotel selectedHotel;
	private NomadAdvisor nomadAdvisor;
	
	private CityFormController cityForm;
	private StatisticsInterface statInterface;
	private HotelFormInterface hotelForm;
	private FXMLLoader fxmlStatLoader;
	private FXMLLoader fxmlCityFormLoader;
	private FXMLLoader fxmlHotelFormLoader;
	private Scene statScene;
	private Scene cityFormScene;
	private Scene hotelFormScene;
	private Stage parentEmployeeStage;
	

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
    
    @FXML private HBox bottomPanel;
    @FXML private Button searchButton;
    @FXML private TextField cityNameField;
    @FXML private HBox buttonBox;
    @FXML private Button newCityButton;
    @FXML private Button deleteCityButton;
    
    @FXML private VBox hotelPanel;
    @FXML private Label hotelTitle;
    //Hotel Table Section
    private ObservableList<Hotel> hotelList = FXCollections.observableArrayList();
    @FXML private TableView<Hotel> hotelTable;
    @FXML private TableColumn<Hotel, String> hotelNameCol;
    @FXML private TableColumn<Hotel, String> hotelAddressCol;
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
    	
    	//retrieve the fxml for the popup windows related to city's form, hotel's form and statistics
    	try {
	    	fxmlStatLoader = new FXMLLoader(EmployeeInterface.class.getResource("resources/StatisticsInterface.fxml"));
	    	fxmlCityFormLoader = new FXMLLoader(EmployeeInterface.class.getResource("resources/CityForm.fxml"));
	    	fxmlHotelFormLoader = new FXMLLoader(EmployeeInterface.class.getResource("resources/HotelFormInterface.fxml"));
	    	statScene = new Scene(fxmlStatLoader.load());
	    	cityFormScene = new Scene(fxmlCityFormLoader.load());
	    	hotelFormScene = new Scene(fxmlHotelFormLoader.load());
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
    	
    	//get popup controllers in order to be able to reload interface with the updated data
    	cityForm = (CityFormController)fxmlCityFormLoader.getController();
    	hotelForm = (HotelFormInterface)fxmlHotelFormLoader.getController();
    	statInterface = (StatisticsInterface)fxmlStatLoader.getController();	
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
    	cityCharCol.setCellValueFactory(new PropertyValueFactory<City, String>("characteristics")); 
    	cityCharCol.setCellFactory(Utils.WRAPPING_CELL_FACTORY);

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
                hotelListUpdate(selectedCity);
	            newHotelButton.setDisable(false);
	            deleteHotelButton.setDisable(false);
           }
        });
    }
    
    private void initializeHotelTable() {
    	hotelNameCol.setCellValueFactory(new PropertyValueFactory<Hotel, String>("hotelName"));
        hotelAddressCol.setCellValueFactory(new PropertyValueFactory<Hotel, String>("address"));;
        webColumn.setCellValueFactory(new PropertyValueFactory<Hotel, String>("website"));
    
        hotelTable.setItems(hotelList);
        
        hotelTable.getSelectionModel().selectedIndexProperty().addListener((num) ->
        {           
            if(hotelTable.getSelectionModel().getSelectedItem() == null) {
               hotelTable.getSelectionModel().clearSelection();

            }
            else {
                selectedHotel = hotelTable.getSelectionModel().getSelectedItem();
                System.out.println("I selected: "+selectedHotel.getHotelName());
            }
        });
    }
   
    public void initInterface() {
    	customerListUpdate();
    }
    
    private void customerListUpdate() {
    	List<Customer> customers = NomadHandler.getCustomers();
    	if(customers == null) 
    		System.out.println("Oops! Something went wrong!");
    	else {
    		customerList.clear();
    		customerList.addAll(customers);
    	}
    }
    private void cityListUpdate(String name){
    	List<City> cities = NomadHandler.getCity(name);
    	if(cities == null) 
    		System.out.println("Oops! Something went wrong!");
    	else {
    		cityList.clear();
    		cityList.addAll(cities);
    	}
    }
    
    private void hotelListUpdate(City choosen){
    	List<Hotel> hotels = NomadHandler.getHotels(choosen);
    	if(hotels == null) 
    		System.out.println("Oops! Something went wrong!");
    	else {
    		hotelList.clear();
    		hotelList.addAll(hotels);
    	}
    }
    
    @FXML
    void searchCity(ActionEvent event) {
    	String cityName = cityNameField.getText().trim();
    	cityListUpdate(cityName);
    		
    } 
    @FXML
    void addCity(ActionEvent event) {
    	
    	Stage popupStage = new Stage();

    	popupStage.setScene(cityFormScene);
    	cityForm.initInterface();
    	popupStage.initOwner(parentEmployeeStage);
    	popupStage.setOnCloseRequest((WindowEvent we) -> {
    		cityListUpdate("");
    	});
    	popupStage.show();
    	
    }

    @FXML
    void addHotel(ActionEvent event) {
    	Stage popupStage = new Stage();
    	popupStage.setScene(hotelFormScene);
    	popupStage.initOwner(parentEmployeeStage);
    	hotelForm.initInterface(selectedCity.getCityName(), selectedCity.getCountryName());
    	popupStage.setOnCloseRequest((WindowEvent we) -> {
           	hotelListUpdate(selectedCity);
    	});
    	popupStage.show();

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
    		customerListUpdate();

    	}
    	else {
    		//retrieve the node related to the table to hide and hide it
    		((VBox)paneChildren.get(paneChildren.indexOf(customerPanel))).setVisible(false);
    		
    		//show the right table
    		((VBox)paneChildren.get(paneChildren.indexOf(cityPanel))).setVisible(true);
    		((VBox)paneChildren.get(paneChildren.indexOf(hotelPanel))).setVisible(true);
    		cityListUpdate("");
    	}
    		
    		
    }

    @FXML
    void deleteCity(ActionEvent event) {
    	logMsg.setText(NomadHandler.deleteCity(selectedCity));
    	cityListUpdate("");
    	hotelListUpdate(selectedCity);
    	cityNameField.setText("");
    }

    @FXML
    void deleteHotel(ActionEvent event) {
    	logMsg.setText(NomadHandler.deleteHotel(selectedHotel));
    	hotelListUpdate(selectedCity);
    }

    @FXML
    void showStatistics(ActionEvent event) {
    	Stage popupStage = new Stage();

    	popupStage.setScene(statScene);
    	popupStage.initOwner(parentEmployeeStage);
    	statInterface.initInterface();
    	popupStage.setOnCloseRequest((WindowEvent we) -> {
           	cityListUpdate("");
    	});
    	popupStage.show();
    }
    
    // Go back to the Login interface
    @FXML void logout(ActionEvent event) {
    	nomadAdvisor.changeScene("loginInterface");
    }
    
    public void setNomadAdvisor(NomadAdvisor nomadAdvisor) {
    	this.nomadAdvisor = nomadAdvisor;
    }
    
    public void setParentStage(Stage stage) {
    	this.parentEmployeeStage = stage;
    }

}
