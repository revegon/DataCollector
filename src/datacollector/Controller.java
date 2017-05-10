/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacollector;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 *
 * @author Xenon
 */
public class Controller {
    
    public void changeScene(String sceneName)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(sceneName));
            DataCollector.window.setScene(new Scene(root));
            
            DataCollector.window.show();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void errorhandle(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.setTitle("ERROR");
        alert.show();
    }
    
    
    
}
