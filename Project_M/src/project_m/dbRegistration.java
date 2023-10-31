package project_m;
/**
 *
 * @author Elios
 */


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class dbRegistration {


    public static ArrayList<Integer> getTestIdAv(LocalDate date){
        ArrayList<Integer> testNA = new ArrayList<Integer>(0);
        ArrayList<Integer> testID = new ArrayList<Integer>(0);
        try {
            dbConnection dbconnect = new dbConnection();
            Connection con = dbconnect.getConnection();
        
            Statement stmt = con.createStatement();
            String Sdate = date.getYear()+"-"+date.getMonthValue()+"-"+date.getDayOfMonth();
            String strSelect = "select ID from `clinic`.`reservation` where date = \" " + Sdate + " \";" ;
            ResultSet rset = stmt.executeQuery(strSelect);
            
            int id;
            while(rset.next()) { 
                id = rset.getInt("ID");
                testNA.add(id);
            }
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        try {
            dbConnection dbconnect = new dbConnection();
            Connection con = dbconnect.getConnection();
        
            Statement stmt = con.createStatement();
            String strSelect = "select ID from `clinic`.`labotest`" ;
            ResultSet rset = stmt.executeQuery(strSelect);
            
            int id;
            while(rset.next()) { 
                id = rset.getInt("ID");
                testID.add(id);
            }
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        testID.removeAll(testNA);
        System.out.println(testID);
        
        return testID;
    }
    
    
    public static String getTestAvailable(LocalDate date){
        
        ArrayList<Integer> testID = getTestIdAv(date);
        
        String testsAvailable="";
        try {
            int ID;
            String type;
            Double price;
            String opt1;
            String opt2;
            
            dbConnection dbconnect = new dbConnection();
            Connection con = dbconnect.getConnection();
            Statement stmt = con.createStatement();

            //
            for(int id: testID){
                System.out.println(id);
            }
            //
            
            String testn;
            for(int id: testID){
                String strSelect = "select ID, type, price, option1, option2 from `clinic`.`labotest` where ID = " + id + ";" ;
                ResultSet rset = stmt.executeQuery(strSelect);

                rset.next();
                ID = rset.getInt("ID");
                type = rset.getString("type");
                price = rset.getDouble("price");
                opt1 = rset.getString("option1");
                opt2 = rset.getString("option2");
                testn = ID+" "+type+" "+price+" "+opt1+" "+opt2;
             
                testsAvailable = testsAvailable + testn + "\n";
            }
            
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        return testsAvailable; 
    }
    
    public static LaboTest getLabTest(int id){
        String[] op = {"",""};
        LaboTest t = new LaboTest(100," ",0,op);
        int ID;
        String type;
        Double price;
        String opt1;
        String opt2;
        
        try{
            dbConnection dbconnect = new dbConnection();
            Connection con = dbconnect.getConnection();
            Statement stmt = con.createStatement();
            
            String strSelect = "select ID, type, price, option1, option2 from `clinic`.`labotest` where ID = " + id + ";" ;
            ResultSet rset = stmt.executeQuery(strSelect);

            rset.next();
            ID = rset.getInt("ID");
            type = rset.getString("type");
            price = rset.getDouble("price");
            opt1 = rset.getString("option1");
            opt2 = rset.getString("option2");


            op[0] = opt1;
            op[1] = opt2;
            t.set(ID, type, price, op);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return t;
    }
    
    
    public static boolean insertReservation(int id, int idpatient,String firstName, String lastName, String date, String time){
        try{
        dbConnection dbconnect = new dbConnection();
        Connection con = dbconnect.getConnection();
        
        //MAKE SURE THAT RESERVATION IS NOT DUPLICATED
        String sql="INSERT INTO `reservation` (`ID`,`idpatient`, `firstname`, `lastname`,`date`,`time`) \n" +
              "SELECT ?,?,?,?,?,? FROM DUAL \n" +
              "WHERE NOT EXISTS (SELECT * FROM `reservation` \n" +
              "WHERE `ID`=? AND `firstname`=? AND `lastname`= ? AND `date`=?  LIMIT 1)";
        
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.setInt(2,idpatient);
        stmt.setString(3,firstName);
        stmt.setString(4,lastName); 
        stmt.setString(5,date);
        stmt.setString(6,time);
        
        stmt.setInt(7,id);
        stmt.setString(8,firstName);
        stmt.setString(9,lastName); 
        stmt.setString(10,date);
        
        int i=stmt.executeUpdate();
            if(i>0){
                return true;
            }
            else{
                return false;
            }
        
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static String getMyReservations(int patientId){
        
        String reservations="";
        try{
           int idReservation;
           int ID;
           String type;
           String date;
           
           dbConnection dbconnect = new dbConnection();
           Connection con = dbconnect.getConnection();
           Statement stmt = con.createStatement();

           String strSelect = 
                   "SELECT `reservation`.`idReservation`,`labotest`.`ID`, `labotest`.`type`, `reservation`.`date`\n" +
                   "FROM `reservation`\n" +
                   "INNER JOIN `labotest` ON `reservation`.`ID` = `labotest`.`ID`\n" +
                   "where `reservation`.`idpatient`="+patientId+";";

           ResultSet rset = stmt.executeQuery(strSelect);
           
           while(rset.next()){
               String reservation="";
               idReservation = rset.getInt("idReservation");
               ID = rset.getInt("ID");
               type = rset.getString("type");
               date = rset.getString("date");
               reservation = idReservation+"               "+ID+"  "+type+"  "+date;
               reservations = reservations + reservation + "\n";
           }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return reservations;
    }
    
    
    public static ArrayList<Integer> getIdReservation(int patientId){
        
        ArrayList<Integer> reservationID = new ArrayList<Integer>(0);
        
        
        try{
           
           dbConnection dbconnect = new dbConnection();
           Connection con = dbconnect.getConnection();
           Statement stmt = con.createStatement();

           String strSelect = "SELECT `idReservation` FROM `reservation` where `idpatient`="+patientId+";";

           ResultSet rset = stmt.executeQuery(strSelect);
           
           int idReservation;
            while(rset.next()) { 
                idReservation = rset.getInt("idReservation");
                reservationID.add(idReservation);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return reservationID;
    }
    
    
    public static boolean deleteReservation(int reservationID){
        try{
            dbConnection dbconnect = new dbConnection();
            Connection con = dbconnect.getConnection();
            Statement stmt = con.createStatement();

            String sql = "delete from `clinic`.`reservation` where idReservation ="+ reservationID +";";
            
            int i=stmt.executeUpdate(sql);
                if(i>0){
                    return true;
                }
                else{
                    return false;
                }

            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    
     public static ArrayList<ArrayList<Integer>> getIdPrice(int patientId){
        
        ArrayList<ArrayList<Integer>> IdPrice = new ArrayList<>(); 
        try{
           dbConnection dbconnect = new dbConnection();
           Connection con = dbconnect.getConnection();
           Statement stmt = con.createStatement();

           String strSelect = "SELECT `labotest`.`ID`,`labotest`.`price` \n"+
                                "FROM `reservation`\n"+
                                "INNER JOIN `labotest` ON `reservation`.`ID` = `labotest`.`ID`\n"+
                                "where `reservation`.`idpatient`="+patientId+";";

           ResultSet rset = stmt.executeQuery(strSelect);
           int i = 0;
           int id; int price;
            while(rset.next()) { 
                id = rset.getInt("ID");
                price = rset.getInt("price");
                
                IdPrice.add(new ArrayList());
                IdPrice.get(i).add(id);
                IdPrice.get(i).add(price);
                i++;

            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return IdPrice;
    }
    
}



