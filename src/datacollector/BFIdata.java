/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacollector;

/**
 *
 * @author Xenon
 */
public class BFIdata {
    
    private String ques;
    private int val;

    public BFIdata(String ques, int val) {
        this.ques = ques;
        this.val = val;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
    
    
}
