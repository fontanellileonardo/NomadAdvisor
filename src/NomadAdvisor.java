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
    private Stage stage;
    private Parent root;
    private FXMLLoader fxmlLoaderHotel;
    private FXMLLoader fxmlLoaderLogin;
    private FXMLLoader fxmlLoaderEmployee;
    private NomadHandler nomadHandler;
    private Scene loginScene;
    private Scene cityScene;
    private Scene hotelScene;
    private Scene employeeScene;
    private Customer loggedCustomer;

    public NomadHandler getNomadHandler() {
        return nomadHandler;
    }

    public void start(Stage stage) {
        nomadHandler = new NomadHandler();
        this.stage = stage;
        this.stage.setTitle("Nomad Advisor");
        try {

	    /*fxmlLoaderLogin = new FXMLLoader(NomadAdvisor.class.getResource("resources/LoginInterface.fxml"));
        root = fxmlLoaderLogin.load();
        loginScen = new Scene(root);
        fxmlLoaderHotel = new FXMLLoader(NomadAdvisor.class.getResource("resources/HotelInterface.fxml"));
        root = fxmlLoaderHotel.load();
        hotelScene = new Scene(root);*/

	    fxmlLoaderEmployee = new FXMLLoader(NomadAdvisor.class.getResource("resources/EmployeeInterface.fxml"));
        root = fxmlLoaderEmployee.load();
        employeeScene = new Scene(root);
          
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*hotelInterface = (HotelInterface) fxmlLoaderHotel.getController();
        hotelInterface.initialize("Pisa", "Italy", loggedCustomer, this);*/
        this.stage.setScene(employeeScene);
        this.stage.show();
    }

    public void changeScene(String newScene) { //per ora solo login-interface
        switch (newScene) {
            case "loginInterface":
                Parent sceneParent = null;
                try {
                    sceneParent = fxmlLoaderLogin.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loginScene = new Scene(sceneParent);
                this.stage.setScene(loginScene);
                this.stage.show();
                break;
            default:
                System.out.println("Not Implemented");
        }
    }
}

