package project_m;

import javafx.application.Application;
import javafx.stage.Stage;

public class Project_M extends Application{
    
    @Override
    public void start(Stage primaryStage){
        LoginFrm loginFrm = new LoginFrm();
        loginFrm.showForm(primaryStage);
    }
     
    public static void main(String[] args) {
        launch(args);    
    }
    
}
