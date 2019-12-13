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
    private Stage stage;
    private Parent root;
    
    
    
    public void start(Stage stage) {
        this.stage = stage;
        
        this.stage.setTitle("Nomad Advisor");
        try {
			this.root = FXMLLoader.load(NomadAdvisor.class.getResource("resources/EmployeeInterface.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }
}

