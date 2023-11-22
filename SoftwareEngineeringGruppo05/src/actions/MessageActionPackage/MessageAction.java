/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions.MessageActionPackage;

import actions.Action;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author maria
 */
public class MessageAction {
    private String message;

    public MessageAction(String message) {
        this.message = message;
    }
    
    public void execute() throws IOException{
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("PopUp.fxml"));
            Parent root1= (Parent) fxmlLoader.load();
            Stage stage= new Stage();
            stage.setTitle("ciao");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }
    }
 
    public void add(Action a){
         throw new UnsupportedOperationException("Cannot add an action to MessageAction.");
    }
    
    public void remove(Action a){
        throw new UnsupportedOperationException("Cannot remove an action from MessageAction.");
    }
}
