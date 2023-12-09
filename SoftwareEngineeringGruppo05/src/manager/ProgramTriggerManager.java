/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import controller.ProgramTriggerController;
import rules.Rule;
import triggers.ProgramTrigger.CreateProgramTrigger;
import triggers.ProgramTrigger.ProgramTrigger;
import triggers.Trigger;

/**
 *
 * @author maria
 */
public class ProgramTriggerManager extends TriggerManager{

     ProgramTrigger trigger;
    
    public ProgramTriggerManager() {
        super("/triggers/ProgramTrigger/ProgramTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        ProgramTriggerController pc = ((ProgramTriggerController)super.getController());
        CreateProgramTrigger cpt = new CreateProgramTrigger();
        trigger = cpt.createProgramTrigger(pc.getFilePath(), pc.getTextArea(), pc.getExitStatus());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}
