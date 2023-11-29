/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rules.Rule;
import rules.RuleManager;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class DeleteRuleConfirmPopUpController implements Initializable {

    @FXML
    private AnchorPane deleteConfirmAnchorPane;
    @FXML
    private Button cancelDeleteActionButton;
    @FXML
    private Button deleteActionButton;
    private RuleManager ruleManager; 
    private Rule rule;
    private HBox ruleCard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //quando l'utente non ha intenzione di cancellare la regola allora semplicemente chiude la finestra
    @FXML
    private void cancelDeleteAction(ActionEvent event) {
        Stage stage = (Stage) deleteConfirmAnchorPane.getScene().getWindow();
        stage.close();
    }
    
    //ho bisogno del rule manager nel caso in cui l'utente voglia cancellare la regola
    public void setRuleManager(RuleManager ruleManager){
        this.ruleManager = ruleManager;
    }
    
    //ho bisogno di capire quale regola devo cancellare, e quindi a quale regola fa riferimento la specifica card.
    public void setRule(Rule rule){
        this.rule = rule;
    }
    //l'utente conferma di cancellare la regola: la regola viene effettivamente eliminata, il popup viene chiuso
    //e la card viene eliminata dalla visualizzazione.
    @FXML
    private void deleteAction(ActionEvent event) {
        ruleManager.deleteRule(rule); 
        ((VBox) ruleCard.getParent()).getChildren().remove(ruleCard);
        Stage stage = (Stage) deleteConfirmAnchorPane.getScene().getWindow();
        stage.close();
    }
    
    //ho bisogno di capire quale card eliminare.
    public void setCard(HBox ruleCard){
        this.ruleCard = ruleCard;
    }


    
    
}
