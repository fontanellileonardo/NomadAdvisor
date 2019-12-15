import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import javafx.event.*;
//import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class NomadAdvisor extends Application {
    
    private LoginInterface loginInterface;
    private HotelInterface hotelInterface;
    private PersonalAreaInterface personalAreaInterface;
    private CityInterface cityInterface;
    private employeeInterface employeeInterface;
    private Stage stage;
    private Parent root;
    private FXMLLoader fxmlLoaderHotel;
    private FXMLLoader fxmlLoaderLogin;
    private FXMLLoader fxmlLoaderCity;
    private FXMLLoader fxmlLoaderEmployee;
    private NomadHandler nomadHandler;
    private Scene loginScene;
    private Scene cityScene;
    private Scene hotelScene;
    private Scene employeeScene;
    private User loggedUser;

    public NomadHandler getNomadHandler() {
        return nomadHandler;
    }

    public void start(Stage stage) {
        nomadHandler = new NomadHandler();
        this.stage = stage;
        this.stage.setTitle("Nomad Advisor");
        try {
        	// Initialize FXML loaders
        	fxmlLoaderCity = new FXMLLoader(NomadAdvisor.class.getResource("resources/CityInterface.fxml"));
        	fxmlLoaderLogin = new FXMLLoader(NomadAdvisor.class.getResource("resources/LoginInterface.fxml"));
        	fxmlLoaderEmployee = new FXMLLoader(NomadAdvisor.class.getResource("resources/EmployeeInterface.fxml"));
        	// Create Scenes
        	this.loginScene = new Scene(fxmlLoaderLogin.load());
        	this.cityScene = new Scene(fxmlLoaderCity.load());
        	this.employeeScene = new Scene(fxmlLoaderEmployee.load());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Get controllers
        loginInterface = (LoginInterface) fxmlLoaderLogin.getController();
        cityInterface = (CityInterface) fxmlLoaderCity.getController();
        employeeInterface = (employeeInterface) fxmlLoaderEmployee.getController();
        // Set the NomadAdvisor reference
        cityInterface.setNomadAdvisor(this);
        loginInterface.setNomadAdvisor(this);
        
        changeScene("loginInterface");
        this.stage.show();
    }

    public void changeScene(String newScene) { //per ora solo login-interface
        switch (newScene) {
            case "loginInterface":
                this.stage.setScene(loginScene);
                break;
            case "personalAreaInterface":
            	System.out.println("personal Area Interface");
            	break;
            case "cityInterface":
            	cityInterface.initialize();
            	cityInterface.setCustomer(loggedUser);
            	this.stage.setScene(cityScene);
            	break;
            case "emplpoyeeInterface":
            	employeeInterface.initialize();
            	this.stage.setScene(employeeScene);
            default:
                System.out.println("Not Implemented");
        }
    }
    
    public void setUser(User user) {
    	loggedUser = user;
    }
}

