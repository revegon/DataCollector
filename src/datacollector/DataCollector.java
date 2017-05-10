/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacollector;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Xenon
 */
public class DataCollector extends Application {
    
    public static Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/BFI_Form.fxml"));
            primaryStage.setTitle("Data Collector");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
