/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacollector;

import database.BFIdataTable;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Xenon
 */
public class BFI_FormController extends Controller implements Initializable {

    @FXML private Label lvl1, lvl2, lvl3, lvl4, lvl5;
    @FXML private VBox container;
    
    private ArrayList<Row> rows;
    private ArrayList<String> quesList;
    private ArrayList<BFIdata> data;
    
//    private String[] quesList = {"asdasdasd", "asdasdada", "asdasdaf", "hgdfgfdg", "asasfasf", "thyrte", "gsdgre"};
    
    
    private static int offset = 0; 
    
    private int a, c, e, n, o; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rows = new ArrayList<>();
        quesList = getQuesList();
        data = new ArrayList<>();
        
        lvl1.setText("Disagree\nStrongly");
        lvl2.setText("Disagree\nSlightly");
        lvl3.setText("Neutral");
        lvl4.setText("Agree\nSlightly");
        lvl5.setText("Agree\nStrongly");
        
        for(int i=offset; i<quesList.size() && i-offset<10;i++)
        {
            Row  r = new Row(quesList.get(i));
            rows.add(r);
            container.getChildren().add(r);
        }
    }    
    
    public void next()
    {
        if(offset>=quesList.size())
        {
            calculateValues();
            storeValues();
            rows.clear();
            offset = 0;
            changeScene("/fxml/front.fxml");
        }
        
        
        try{
            for(Row r : rows)
            {
                String ques = r.getQuestion();
                int val = Integer.parseInt(r.getSelected());
                
                BFIdata d = new BFIdata(ques, val);
                data.add(d);
            }
            
            rows.clear();
            offset += 10;
            changeScene("/fxml/BFI_Form.fxml");
        }catch(NullPointerException e)
        {
           errorhandle(e.getMessage());
           return;
        }
    }
    
    private ArrayList<String> getQuesList()
    {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("res/QuestionList.txt"));
            while(sc.hasNext())
            {
                String s = sc.nextLine();
                list.add(s);
            }
            System.out.println(list.size());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BFI_FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private void calculateValues() {
         e = 20 + data.get(1).getVal()-data.get(6).getVal()+data.get(11).getVal()-data.get(16).getVal()+data.get(21).getVal()-data.get(26).getVal()+data.get(31).getVal()-data.get(36).getVal()+data.get(41).getVal()-data.get(46).getVal();
         a = 14 + data.get(2).getVal()-data.get(7).getVal()+data.get(12).getVal()-data.get(17).getVal()+data.get(22).getVal()-data.get(27).getVal()+data.get(32).getVal()-data.get(37).getVal()+data.get(42).getVal()-data.get(47).getVal();
         c = 14 + data.get(3).getVal()-data.get(8).getVal()+data.get(13).getVal()-data.get(18).getVal()+data.get(23).getVal()-data.get(28).getVal()+data.get(33).getVal()-data.get(38).getVal()+data.get(43).getVal()-data.get(48).getVal();
         n = 38 + data.get(4).getVal()-data.get(9).getVal()+data.get(14).getVal()-data.get(19).getVal()+data.get(24).getVal()-data.get(29).getVal()+data.get(34).getVal()-data.get(39).getVal()+data.get(44).getVal()-data.get(49).getVal();
         o = 8 + data.get(5).getVal()-data.get(10).getVal()+data.get(15).getVal()-data.get(20).getVal()+data.get(25).getVal()-data.get(30).getVal()+data.get(35).getVal()-data.get(40).getVal()+data.get(45).getVal()-data.get(50).getVal();
        
        
    }

    private void storeValues() {
        
        BFIdataTable dt = new BFIdataTable();
        Document doc = new Document();
        doc.append("Name", FrontController.name);
        doc.append("reg", FrontController.reg);
        doc.append("Extraversion", e);
        doc.append("Agreeableness", a);
        doc.append("Conscientiousness", c);
        doc.append("Neuroticism ", n);
        doc.append("Openness", o);
        doc.append("dataid", dt.getIndex());
        dt.insert(doc);
    }
    
    
    
}
