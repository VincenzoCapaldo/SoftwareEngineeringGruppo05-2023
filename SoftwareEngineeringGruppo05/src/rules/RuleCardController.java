package rules;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import rules.Rule;
import rules.Rule;
import rules.RuleManager;
import rules.RuleManager;

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
                
                //apro il popUp in una nuova finestra
                Stage stage = new Stage();
                stage.setTitle("Delete the rule " + rule.getName() + "?");
                stage.setScene(new Scene(root2));
                stage.show();

            }catch(Exception e){
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


    //restituisce all'utente un feedback visivo quando la regola cambia stato (attiva-disattiva)
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
            ruleManager.reactivateRule(rule);
            ruleBox.getStyleClass().add("main_background");
            ruleBox.getStyleClass().remove("deactivated_rule_background");
        } else {
            ruleManager.deactivateRule(rule);
            ruleBox.getStyleClass().add("deactivated_rule_background");
            ruleBox.getStyleClass().remove("main_background");
        }
    }

    
}
