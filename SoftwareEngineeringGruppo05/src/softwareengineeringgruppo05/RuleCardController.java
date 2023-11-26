/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rules.Rule;
import rules.RuleManager;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class RuleCardController implements Initializable {

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
        
    }

   @FXML
    private void deleteRuleAction(MouseEvent event) {
        ruleManager.deleteRule(rule);
        ((VBox) ruleBox.getParent()).getChildren().remove(ruleBox);
    }
    
    public void setRuleManager(RuleManager ruleManager){
        this.ruleManager = ruleManager;
    }
    
    public void setRule(Rule rule){
        this.rule = rule;
    }
    
    public void setData(){
        this.ruleName.setText(rule.getName());
    }

    @FXML
    private void changeStateRule(ActionEvent event) {
        if (stateRule.isSelected()) {
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
