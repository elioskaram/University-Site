/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_m;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javafx.stage.Stage;

/**
 *
 * @author Elios
 */
public class dbLogin {
    public static void logindata(Stage primaryStage, String email, String password){
        //function (email,pass)
            try{
                dbConnection dbconnect = new dbConnection();
                Connection con = dbconnect.getConnection();

                Statement stmt = con.createStatement();

                String strSelect = "select address, password, isAdmin from `clinic`.`patient`";
                ResultSet rset = stmt.executeQuery(strSelect);

                //int rowCount = 0;
                while(rset.next()) { 
                    String add = rset.getString("address");
                    String pass = rset.getString("password");
                    int isAdmin = rset.getInt("isAdmin");
                    //System.out.println(add + "  " + pass);
                    
                    
                    if(add.equals(email) && pass.equals(MD5.getMd5(password))){
                        
                        if(isAdmin == 1){
                            System.out.println("amdin: \n");
                            Scanner input = new Scanner(System.in); 
                            int opt;
                            while(true){
                                System.out.println("Choose an number: 1)insert 2)modify 3)delete 4)exit");
                                opt = input.nextInt();
                                if(opt==1){
                                    dbAdmin.insertLaboTest();
                                }
                                else if(opt==2){
                                    dbAdmin.modifyLaboTest();
                                }
                                else if(opt==3){
                                    dbAdmin.deleteLaboTest();
                                }
                                else if(opt==4){
                                    break;
                                }
                            }
                            input.close();
                        }
                        else{
                            Patient pat = dbLogin.selectPatient(add);
                            PatientFrm patientFrm = new PatientFrm();
                            patientFrm.showPatientFrm(primaryStage, pat);
                            System.out.println("patient logged in");
                        }
                    }
                }

            }
            catch(Exception ex){
                System.err.println(ex.getMessage());
            }
    }
    public static Patient selectPatient(String email){
        
        Patient p = new Patient(0," "," "," "," "," ");
        
        int id;
        String firstName;
        String lastName;
        String password;
        String tel;
        
        try{
            dbConnection dbconnect = new dbConnection();
            Connection con = dbconnect.getConnection();
            
            Statement stmt = con.createStatement();
            String strSelect = "select idpatient, firstname, lastname, password, tel from `clinic`.`patient` where address = \"" + email + "\" ;" ;
            ResultSet rset = stmt.executeQuery(strSelect);

            rset.next();
            id = rset.getInt("idpatient");
            firstName = rset.getString("firstname");
            lastName = rset.getString("lastname");
            password = rset.getString("password");
            tel = rset.getString("tel");
            
            p.set(id, firstName, lastName, password, email, tel);
            
            
            
        }
        catch(Exception ex){
                System.err.println(ex.getMessage());
        }
        return p;
    }
    
}
