package project_m;
/**
 *
 * @author Elios
 */

import static java.lang.Integer.parseInt;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

public class PatientFrm {
    
    Label lblDate;
    Label lblTest;
    Label lblTime;
    Label lblmsg;
    Label lbldeletemsg;
    Label lbldelete;
    Label lblTotal;
    
    DatePicker dpReservation;
    TextArea taTestAvailable;
    TextField tfTime;
    ChoiceBox cbTest;
    ChoiceBox cbmyRes;
    
    Button btnSubmit;
    Button btnShow;
    Button btnBack;
    Button btnCalcInvoice;
    
    
    
    
    public void showPatientFrm(Stage s, Patient p){
        
        lblDate = new Label("Pick a reservation date: ");
        lblTest = new Label("Pick a test: ");
        lblTime = new Label("time: hh:mm:ss ");
        lblmsg = new Label("");
        lbldeletemsg = new Label("");
        lbldelete = new Label("pick to delete:");
        lblTotal = new Label("");
        
        dpReservation = new DatePicker();
        taTestAvailable = new TextArea();
        tfTime = new TextField();
        cbTest = new ChoiceBox();
        cbmyRes = new ChoiceBox();
        btnSubmit = new Button("Submit");
        btnShow = new Button("Show My Reservations");
        btnBack = new Button("Back");
        btnCalcInvoice = new Button("Invoice");
        
        
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20, 50, 50, 50));
        
        
        btnCalcInvoice.setMinSize(145,45);

        
        taTestAvailable.setEditable(false);
                
        lblDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        // show week numbers
        dpReservation.setShowWeekNumbers(true);
        
        //add to root
        root.add(lblDate,0,1,3,1);
        root.add(dpReservation,1,2);
        root.add(taTestAvailable,0,3,4,1);
        root.add(lblTest,1,4);
        root.add(lblTime, 2,4);
        root.add(cbTest,1,5);
        root.add(tfTime,2,5);
        root.add(btnSubmit,3,5);
        root.add(lblmsg,0,6,3,1);
        root.add(btnShow,1,8,2,1);
        root.add(lbldelete,2,7);
        root.add(cbmyRes,2,8);
        root.add(lbldeletemsg,2,9);
        root.add(btnCalcInvoice,1,10);
        root.add(lblTotal,1,11,2,1);
        root.add(btnBack,3,11);
        
        GridPane.setHalignment(btnCalcInvoice, HPos.LEFT);
        GridPane.setHalignment(btnBack, HPos.RIGHT);

                
        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e)
            {
                
                // get the date picker value
                LocalDate date = dpReservation.getValue();
                String text = dbRegistration.getTestAvailable(date);
                String titles = "ID type price option1 optoin2 \n";
                taTestAvailable.setText(titles + text);
                
                ArrayList<Integer> ids = dbRegistration.getTestIdAv(date);
                cbTest.getItems().setAll(ids);
                
                
                
                //testing
                String s = date.getDayOfMonth()+"-"+  date.getMonthValue()+"-"+ date.getYear();
                System.out.println(s);
            }
            
            
        };
        
        // when datePicker is pressed
        dpReservation.setOnAction(event);
        
        //btn events
        btnSubmit.setOnAction(e -> btnSubmit(p));
        btnShow.setOnAction(e -> btnShow(p));
        cbmyRes.setOnAction(e -> deleteRes(p));
        btnBack.setOnAction(e -> goBack(s));
        btnCalcInvoice.setOnAction(e -> saveInvoice(p)); 
        
        // create a scene
        Scene sc = new Scene(root, 520, 600);
        s.setTitle("Reservation"); // Set title
        s.setScene(sc); // Place the scene in the stage
        s.show(); // Display the stage 
    }
    
    private void btnSubmit(Patient p){
        if(valid()){
            if(GFG.isValidTime(tfTime.getText())){
                
                int testID = (int)cbTest.getValue();
                
                int atIndex = cbTest.getItems().indexOf(testID);
                cbTest.getItems().remove(atIndex);
                //so the patient could not select the same test twice at the same date. 

                LaboTest t = dbRegistration.getLabTest(testID);
                
                System.out.println(t.getOptions()[1]);
                
                LocalDate dpDate = dpReservation.getValue();
                date test_date = new date(dpDate.getDayOfMonth(),dpDate.getMonthValue(),dpDate.getYear());
                System.out.println(test_date.getAnnee()+" "+test_date.getMois()+" "+test_date.getJour());
                t.setTest_date(test_date);
                
                String time = tfTime.getText();
                String hh = time.substring(0,2);
                String mm = time.substring(3,5);
                String ss = time.substring(6,8);
                int h = parseInt(hh);
                int m = parseInt(mm);
                int s = parseInt(ss);
                System.out.println(h+" "+m+" "+s);
                
                Time test_Time = new Time(h,m,s);
                t.setTest_time(test_Time);
                p.setTest(t);
                
                String date = test_date.getAnnee()+"-"+test_date.getMois()+"-"+test_date.getJour();
                boolean isInserted = dbRegistration.insertReservation(testID,p.getID(),p.getFirstName(),p.getLastName(),date,time);
                
                if(isInserted){
                    lblmsg.setTextFill(Color.BLUE);
                    lblmsg.setText("successfully inserted!");
                }
                else{
                    lblmsg.setTextFill(Color.RED);
                    lblmsg.setText("reservation for this test is already taken.");
                }
                
            }
            else{
                lblmsg.setTextFill(Color.RED);
                lblmsg.setText("error input: Time format is wrong.");
            }
        }
        else{
            lblmsg.setTextFill(Color.RED);
            lblmsg.setText("error input: select the ID and Time.");
        }
        
        //cbTest.getValue();
    }
    
    
    private void btnShow(Patient p){
        String text = dbRegistration.getMyReservations(p.getID());
        String titles = "YOUR RESERVATIONS:\n\nidReservation   ID   type   date \n";
        taTestAvailable.setText(titles + text);

        ArrayList<Integer> reservationId = dbRegistration.getIdReservation(p.getID());
        cbmyRes.getItems().setAll(reservationId);
    }
    
    
    private void deleteRes(Patient p){
        
        int reservationID = (int)cbmyRes.getValue();
        boolean isDeleted = dbRegistration.deleteReservation(reservationID);
        if(isDeleted){
            lbldeletemsg.setTextFill(Color.BLUE);
            lbldeletemsg.setText("Reservation deleted.");
        }else{
            lbldeletemsg.setTextFill(Color.RED);
            lbldeletemsg.setText("Reservation: not deleted.");
        }
        btnShow(p);
    }
    
    
    private boolean valid(){
        return (!cbTest.getSelectionModel().isEmpty() && !lblTime.getText().isEmpty());
    }
    
    private void goBack(Stage primaryStage){
        LoginFrm login = new LoginFrm();
        login.showForm(primaryStage);
    }
    
    
    

    
    private void saveInvoice(Patient p){
        Map<Integer, String> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> idPrice = dbRegistration.getIdPrice(p.getID()); 
        double total=0;
        int ID;
        LaboTest t;
        String testsReserved = "";
        int Count = idPrice.size();
        
        for (int i = 0; i < Count; i++) {
            int price = idPrice.get(i).get(1);
            total += price;
            ID = idPrice.get(i).get(0);
            t = dbRegistration.getLabTest(ID);
            String tr = t.getType()+" "+t.getPrice()+"\n";
            map.put(price,tr);
        }   
        // TreeMap to store values of HashMap
        TreeMap<Integer,String> sorted = new TreeMap<>();
        // Copy all data from hashMap into TreeMap
        sorted.putAll(map);

        for (Map.Entry<Integer,String> entry : sorted.entrySet()){
            testsReserved += entry.getValue();
        }
        total=total*1.11;
        lblTotal.setText("Total: "+total+" $");
        String userName = p.getFirstName() + p.getLastName();
        String Invoice = userName+"\n\n"+testsReserved+"\n Total: "+total;
        
        FileInvoice.writeInvoice(Invoice);
        
    }
}