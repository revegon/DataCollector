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
import javafx.scene.control.ChoiceBox;
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
    @FXML private ChoiceBox<String> language;
    
    public static String name;
    public static String reg;
    public static String[] languages = {"English (recommanded)", "Bangla"};
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        language.getItems().addAll(languages);
        language.setValue(languages[0]);
    } 
    
    public void next(ActionEvent ae)
    {
         name = nameField.getText();
         reg = regField.getText();
        String accessCode = codeField.getText();
        String lang = language.getSelectionModel().getSelectedItem();
        if(lang.contains("English"))
        {
            BFI_FormController.getQuesList("en");
        }
        else BFI_FormController.getQuesList("bn");
        
        if(name.isEmpty() || reg.isEmpty() || accessCode.isEmpty())
        {
            errorhandle("Fill all fields!!");
            return;
        }
        
        Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Crawler crawler = new Crawler(name, reg);
                    crawler.crawl(codeField.getText());
                }
         });
        t.start();
        
        changeScene("/fxml/BFI_Form.fxml");
    }

    
    
}
