/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacollector;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Xenon
 */
public class FrontController extends Controller implements Initializable {
    
    @FXML private TextField nameField;
    @FXML private TextField regField;
    @FXML private TextArea codeField;
    
    public static String name;
    public static String reg;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void next(ActionEvent ae)
    {
         name = nameField.getText();
         reg = regField.getText();
        String accessCode = codeField.getText();
        
        if(name.isEmpty() || reg.isEmpty() || accessCode.isEmpty())
        {
            errorhandle("Fill all fields!!");
            return;
        }
        
        
        
        changeScene("/fxml/BFI_Form.fxml");
    }
    
}
