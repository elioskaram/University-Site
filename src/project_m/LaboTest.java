/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_m;

/**
 *
 * @author Elios
 */
public class LaboTest {
    private int num;
    private String type;
    private double price;
    private String[] options = {" "," "};
    private date test_date;
    private Time test_time;
    private String resultat;

    public LaboTest(int num, String type, double price, String[] options) {
        this.num = num;
        this.type = type;
        this.price = price;
        this.options = options;
        //this.test_date.setD(test_date);
        //this.test_time.setT(test_time);
        //this.resultat = resultat;
    }

    public void set(int num, String type, double price, String[] options){
        this.num = num;
        this.type = type;
        this.price = price;
        this.options = options;
        //this.test_date.setD(t.getTest_date());
        //this.test_time.setT(t.getTest_time());
        //this.resultat = t.getResultat();
    }
    
    public date getTest_date() {
        return test_date;
    }

    public Time getTest_time() {
        return test_time;
    }
    
    public int getNum() {
        return num;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String[] getOptions() {
        return options;
    }

    public String getResultat() {
        return resultat;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setTest_date(date test_date) {
        this.test_date = test_date; 
    }

    public void setTest_time(Time test_time) {
        this.test_time = test_time;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
    
    
}
