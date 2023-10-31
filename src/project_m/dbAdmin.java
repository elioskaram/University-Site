/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_m;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Elios
 */
public class dbAdmin {
     public static void insertLaboTest(){
        
        
        Scanner input = new Scanner(System.in);
       
        
        System.out.println("Enter informations: ");
        System.out.println("Enter ID: ");
        int id = input.nextInt();
        String type;
        double price;
        String opt1;
        String opt2;
        
        if(dbIsPresent(id) == 0){
            System.out.println("Enter type:");
            input.nextLine();
            type = input.nextLine();

            System.out.println("Enter price:");
            price = input.nextDouble();
            input.nextLine();

            System.out.println("Enter option 1:");
            opt1 = input.nextLine();

            System.out.println("Enter option 2:");
            opt2 = input.nextLine();

            try{
                dbConnection dbconnect = new dbConnection();
                Connection con = dbconnect.getConnection();
                
                String strinsert="INSERT INTO `clinic`.`labotest` (`ID`, `type`, `price`, `option1`, `option2`) VALUES (?,?,?,?,?);";


                PreparedStatement stmt1 = con.prepareStatement(strinsert);
                stmt1.setInt(1,id);
                stmt1.setString(2,type);
                stmt1.setDouble(3,price); 
                stmt1.setString(4,opt1);
                stmt1.setString(5,opt2);

                int i=stmt1.executeUpdate();
                if(i>0){
                    System.out.println("lab test inserted");
                }
                else{
                    System.out.println("lab test not inserted");
                }

            }
            catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        }
        else{
            System.out.println("already exists");
        }
    }
    
    public static void selectLaboTest(){
        
        try{
            dbConnection dbconnect = new dbConnection();
            Connection con = dbconnect.getConnection();
            Statement stmt = con.createStatement();

            String strSelect = "select * from `clinic`.`labotest`";
            ResultSet rset = stmt.executeQuery(strSelect);
            
            while(rset.next()) { 
                int id = rset.getInt("ID");
                String type = rset.getString("type");
                Double price = rset.getDouble("price");
                String opt1 = rset.getString("option1");
                String opt2 = rset.getString("option2");
                System.out.println(id+"  "+type+" "+price+" "+opt1+" "+opt2);
            } 
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public static void modifyLaboTest(){
        
        Scanner input = new Scanner(System.in);
        selectLaboTest();
        System.out.println("choose an id to modify the row: ");
        int id = input.nextInt();
        int isPresent = dbIsPresent(id);
        
            
        if(isPresent == 1){
            System.out.println("Enter type:");
            input.nextLine();
            String type = input.nextLine();

            System.out.println("Enter price:");
            double price = input.nextDouble();
            input.nextLine();

            System.out.println("Enter option 1:");
            String opt1 = input.nextLine();

            System.out.println("Enter option 2:");
            String opt2 = input.nextLine();
            System.out.println("update");
            
            try{
                dbConnection dbconnect = new dbConnection();
                Connection con = dbconnect.getConnection();
                String query = "update clinic.labotest set type=?, price=?, option1=?, option2=? where ID = ?;";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, type);
                preparedStmt.setDouble(2, price);
                preparedStmt.setString(3, opt1);
                preparedStmt.setString(4, opt2);
                preparedStmt.setInt(5, id);

                // execute the java preparedstatement
                preparedStmt.executeUpdate();
                System.out.println("updated");
            }
            catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        }
        else{
            System.out.println("not updated");
        }
    }
    
    public static void deleteLaboTest(){
        Scanner input = new Scanner(System.in);
        selectLaboTest();
        System.out.println("choose an id to delete the row: ");
        int id = input.nextInt();
        int isPresent = dbIsPresent(id);
        
        if(isPresent == 1){
            try{
                dbConnection dbconnect = new dbConnection();
                Connection con = dbconnect.getConnection();
                String query = "delete from clinic.labotest where id = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, id);
                preparedStmt.execute();

                
              }
              catch (Exception e)
              {
                System.err.println(e.getMessage());
              }
            }
        else{
            System.out.println("no id "+ id);
        }
        
    }
    
    
    private static int dbIsPresent(int id){
        try{
            dbConnection dbconnect = new dbConnection();
            Connection con = dbconnect.getConnection();
            String strSelect = "SELECT EXISTS(SELECT * from clinic.labotest WHERE ID="+ id +");";
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);
            rset.next();
            return rset.getInt(1);
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
