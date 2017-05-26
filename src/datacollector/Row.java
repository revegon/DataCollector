/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacollector;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Xenon
 */
public class Row extends HBox{

    private String question;
    
    private ToggleGroup tg;
    
    public Row(String ques) {
        setPrefWidth(560);
        setPrefHeight(20);
        setSpacing(27);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        this.question = ques;
        Label quesLabel = new Label(question);
        quesLabel.setTooltip(new Tooltip(question));
        quesLabel.setPrefSize(290, 45);
        quesLabel.setPadding(new Insets(0, 0, 0, 5));
//        quesLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        tg = new ToggleGroup();
        
        RadioButton lvl1 = new RadioButton();
        RadioButton lvl2 = new RadioButton();
        RadioButton lvl3 = new RadioButton();
        RadioButton lvl4 = new RadioButton();
        RadioButton lvl5 = new RadioButton();
        
        lvl1.setToggleGroup(tg);
        lvl2.setToggleGroup(tg);
        lvl3.setToggleGroup(tg);
        lvl4.setToggleGroup(tg);
        lvl5.setToggleGroup(tg);
        
        lvl1.setUserData("1");
        lvl2.setUserData("2");
        lvl3.setUserData("3");
        lvl4.setUserData("4");
        lvl5.setUserData("5");
        
        getChildren().addAll(quesLabel, lvl1, lvl2, lvl3, lvl4, lvl5);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    
    public String getSelected() throws NullPointerException
    {
        if(tg.getSelectedToggle()==null) 
        {
            throw new NullPointerException("Nothing is Selected");
        }
        return (String) tg.getSelectedToggle().getUserData();
    }
    
    
}