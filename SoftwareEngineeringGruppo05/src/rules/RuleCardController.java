package rules;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import rules.Rule;
import rules.RuleManager;
import javafx.stage.Modality;


/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class RuleCardController implements Observer, Initializable {

    @FXML
    private HBox ruleBox;
    @FXML
    private Label ruleName;
    private RuleManager ruleManager;
    private Rule rule;
    @FXML
    private CheckBox stateRule;
    @FXML
    private ImageView deletePicker;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ruleManager = RuleManager.getInstance();
    }

   @FXML
    private void deleteRuleAction(MouseEvent event) {
        
        try{
            //carico il file fxml relativo alla finestra di conferma cancellazione regola
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/rules/DeleteRuleConfirmPopUp.fxml"));
            Parent root2= (Parent) fxmlLoader.load();

            //carico il controller del popup per passargli le informazioni che ha bisogno per effettuare le azioni
            //di "cancel" e "delete"
            DeleteRuleConfirmPopUpController deleteRuleConfirmPopUpController = fxmlLoader.getController();
            deleteRuleConfirmPopUpController.setRule(rule);
            deleteRuleConfirmPopUpController.setCard(ruleBox);
                
            // Crea una nuova finestra modale
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(deletePicker.getScene().getWindow());

            modalStage.setTitle("Delete " + rule.getName() + "?");
            modalStage.getIcons().add(new Image("/css/email.png"));
            modalStage.setScene(new Scene(root2));
            
            // Mostra la finestra modale e blocca l'interazione con la finestra sottostante
            modalStage.showAndWait();

        }catch(IOException e){
            System.out.println("Cant load new window");
        }
        
    }
        
    //ho bisogno di capire a quale regola fa riferimento la card
    public void setRule(Rule rule){
        this.rule = rule;
        setData();
        if (rule.getState()) {
            ruleBox.getStyleClass().add("main_background");
            ruleBox.getStyleClass().remove("deactivated_rule_background");
        } else {
            stateRule.setSelected(false);
            ruleBox.getStyleClass().add("deactivated_rule_background");
            ruleBox.getStyleClass().remove("main_background");
        }
    }
    
    //setto il nome della card con il nome della regola
    public void setData(){
        this.ruleName.setText(rule.getName());
    }


    //attiva la regola se la checkBox è selezionata, altrimenti la disattiva
    @FXML
    private void changeStateRule(ActionEvent event) {
        if (stateRule.isSelected()) {
            ruleManager.reactivateRule(rule);
        } else {
            ruleManager.deactivateRule(rule);
        }
    }

    @Override
    public void update(Observable subject, Object arg) {
        Rule ruleSubject = (Rule)subject;
        if(ruleSubject.getState()){
            ruleBox.getStyleClass().add("main_background");
            ruleBox.getStyleClass().remove("deactivated_rule_background");
        } else {
            stateRule.setSelected(false);
            ruleBox.getStyleClass().add("deactivated_rule_background");
            ruleBox.getStyleClass().remove("main_background");
        }
    }

    
}
