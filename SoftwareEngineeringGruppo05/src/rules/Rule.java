/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rules;

import actions.Action;
import triggers.Trigger;

/**
 *
 * @author maria
 */
public class Rule {
    private String name;
    private Action action;
    private Trigger trigger;
    
    public Rule(String name, Action action, Trigger trigger){
        this.name = name;
        this.action = action;
        this.trigger = trigger;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Action getAction(){
        return action;
    }
    
    public void setAction(Action action){
        this.action = action;
    }
    
    public Trigger getTrigger(){
        return trigger;
    }
    
    public void setTrigger(Trigger trigger){
        this.trigger = trigger;
    }
    
    public void checkTrigger(){ 
        if(trigger.verify()){
            action.execute();
        }
    }
}
