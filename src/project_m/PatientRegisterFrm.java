package project_m;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PatientRegisterFrm {
    Patient p;
    
    Label firstNamelbl;
    Label lastNamelbl;
    Label passlbl;
    Label addresslbl;
    Label tellbl;
    Label errorlbl;
    
    TextField firstNametf;
    TextField lastNametf;
    PasswordField passpf;
    TextField addresstf;
    TextField teltf;
    
    Button submit;
    Button btBack;
    
    
    public void showForm(Stage primaryStage){
        firstNamelbl = new Label("First Name: ");
        lastNamelbl = new Label("Last Name: ");
        passlbl = new Label("Password: ");
        addresslbl = new Label("Address: ");
        tellbl = new Label("telephone: ");
        errorlbl = new Label(" ");
        
        
        firstNametf = new TextField();
        lastNametf = new TextField();
        passpf = new PasswordField();
        addresstf = new TextField();
        teltf = new TextField();
                
        
        submit = new Button("submit");
        btBack = new Button("back");
        
        submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                boolean connect = validate_form();
                if(connect){
                    //save in patient class
                    boolean result = dbPatientRegister.insertPatient(firstNametf.getText(), lastNametf.getText(), passpf.getText(), addresstf.getText(), teltf.getText());
                    if(result){
                        errorlbl.setText("patient inserted.");
                    }
                    else{
                        errorlbl.setText("patient not inserted.");
                    }
                }
                else{
                    errorlbl.setTextFill(Color.RED);
                }
                
            }
        });
        
        
        btBack.setOnAction(e -> goBack(primaryStage));
        
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        
        root.add(firstNamelbl,0,0);
        root.add(firstNametf,1,0);
        root.add(lastNamelbl,0,1);
        root.add(lastNametf,1,1);
        root.add(passlbl,0,2);
        root.add(passpf,1,2);
        root.add(addresslbl,0,3);
        root.add(addresstf,1,3);
        root.add(tellbl,0,4);
        root.add(teltf,1,4);
        
        root.add(errorlbl,0,6);
        
        root.add(submit, 1, 5);
        root.add(btBack, 2,7);
       
        root.setAlignment(Pos.CENTER);
        root.getColumnConstraints().add(new ColumnConstraints(110));
        
        Scene scene = new Scene(root,400,400);
        primaryStage.setTitle("Patient Register"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        
               
}
    
    private void goBack(Stage primaryStage){
        LoginFrm login = new LoginFrm();
        login.showForm(primaryStage);
    }
    
    
    
    public boolean validate_form(){
        boolean proceed = true;
        if(firstNametf.getText().isEmpty() && proceed == true){
            errorlbl.setText("Enter firstName.");
            proceed = false;
        }
        
        if(lastNametf.getText().isEmpty() && proceed == true){
            errorlbl.setText("Enter LastName.");
            proceed = false;
        }
        
        String p = passpf.getText();
        if(p.isEmpty() && proceed == true){
            errorlbl.setText("Enter a password");
            proceed = false;
        }
        if(!p.isEmpty() && proceed == true){
            if (p.length() >= 8){
                Pattern letter = Pattern.compile("[a-zA-z]");
                Pattern digit = Pattern.compile("[0-9]");
                Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

                Matcher hasLetter = letter.matcher(p);
                Matcher hasDigit = digit.matcher(p);
                Matcher hasSpecial = special.matcher(p);

                if(!(hasLetter.find() && hasDigit.find() && hasSpecial.find())){
                    errorlbl.setText("Enter valid password");
                    proceed = false;
                }
                else{
                    errorlbl.setText(" ");
                    proceed = true;
                }
            }
            else{
                errorlbl.setText("password short.");
                proceed = false;
            }
        }
        
        
        String add = addresstf.getText();
        if(add.isEmpty() && proceed == true){
            errorlbl.setText("Enter Address.");
            proceed = false;
        }
        if(!add.isEmpty() && proceed == true){
            if(!EmailValidatorStrict.isValid(add)){
                errorlbl.setText("Enter valid Address");
                proceed =false;
            }
        }
        
        String phone = teltf.getText();
        if(phone.isEmpty() && proceed == true){
            errorlbl.setText("Enter Phone Number.");
            proceed = false;
        }
        if(!phone.isEmpty() && proceed == true){
            if(!isValidPhone(phone)){
                proceed = false;
            }
        }
        
        return proceed;
    }
    
    public static boolean isValidPhone(String s)
    {
        Pattern p = Pattern.compile("^\\d{8}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }
    
}
