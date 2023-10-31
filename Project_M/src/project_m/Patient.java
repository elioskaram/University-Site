/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_m;

import java.sql.*;
/**
 *
 * @author Elios
 */
public class Patient {
    private int ID;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String tel;
    private LaboTest test;

    public Patient(int ID, String firstName, String lastName, String password, String address, String tel) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.tel = tel;
    }
    
    public void set(int ID, String firstName, String lastName, String password, String address, String tel){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.tel = tel;
    }
            

    public LaboTest getTest() {
        return test;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setTest(LaboTest test) {
        this.test = test;
    }
    
}