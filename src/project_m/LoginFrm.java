package project_m;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.*;



public class LoginFrm {
    
    
    Label lblLogin;
    Label lblEmail;
    Label lblPassword;
    Label lblSignup;
    TextField tfEmail;
    PasswordField pfPassword;
    Button btLogin;
    Button btSignup;
    
    
    public void showForm(Stage primaryStage){
        lblLogin = new Label("Login:");
        lblEmail = new Label("Email:");
        lblPassword = new Label("Password:");
        lblSignup = new Label("New User:");
        
        tfEmail = new TextField();
        pfPassword = new PasswordField();
        
        btLogin = new Button("Login");
        btSignup = new Button("Sign up");
        
        
        
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        
        lblLogin.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        lblSignup.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        root.add(lblLogin,0,0,2,1);
        root.add(lblEmail,0,1);
        root.add(tfEmail,1,1);
        root.add(lblPassword,0,2);
        root.add(pfPassword,1,2);
        root.add(btLogin,1,3);
        root.add(lblSignup,0,5,2,1);
        root.add(btSignup, 1, 6);
        root.setAlignment(Pos.CENTER);
        
        //events
        btSignup.setOnAction(e -> goToRegister(primaryStage));
        btLogin.setOnAction(e -> Login(primaryStage));
        
        
        Scene scene1 = new Scene(root,400,400);
        primaryStage.setTitle("LOGIN"); // Set title
        primaryStage.setScene(scene1); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        
    }
    
    private void goToRegister(Stage primaryStage){
        PatientRegisterFrm pFrm = new PatientRegisterFrm();
        pFrm.showForm(primaryStage);
    }
    
    private void Login(Stage primaryStage){
        
        String email = tfEmail.getText();
        String password = pfPassword.getText();
        
        boolean v = valid(); //checks if email and password fields are not empty
        if(v)
        {
            dbLogin.logindata(primaryStage, email, password);
        }
    }
    
    private boolean valid(){
        return (!tfEmail.getText().isEmpty() && !pfPassword.getText().isEmpty());
    }
   
    
    
   
}
