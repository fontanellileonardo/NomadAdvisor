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
    private Scene loginScene;
    
    
    
    public void start(Stage stage) {
        this.stage = stage;
        
        this.stage.setTitle("Nomad Advisor");
        try {
			fxmlLoaderLogin = new FXMLLoader(NomadAdvisor.class.getResource("resources/LoginInterface.fxml"));
            fxmlLoaderHotel = new FXMLLoader(NomadAdvisor.class.getResource("resources/HotelInterface.fxml"));
            this.root = fxmlLoaderHotel.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        hotelInterface = (HotelInterface) fxmlLoaderHotel.getController();
        hotelInterface.initialize("Pisa", "Italy", this);
        this.stage.setScene(new Scene(root));
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

