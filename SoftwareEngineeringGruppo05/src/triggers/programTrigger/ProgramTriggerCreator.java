package triggers.programTrigger;

import triggers.TriggerCreator;
import triggers.programTrigger.ProgramTriggerController;
import rule.Rule;
import triggers.programTrigger.CreateProgramTrigger;
import triggers.programTrigger.ProgramTrigger;
import triggers.Trigger;

/**
 *
 * @author maria
 */
public class ProgramTriggerCreator extends TriggerCreator{

    ProgramTrigger trigger;
    
    public ProgramTriggerCreator() {
        super("/triggers/programTrigger/ProgramTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        ProgramTriggerController ptc = ((ProgramTriggerController)super.getController());
        CreateProgramTrigger cpt = new CreateProgramTrigger();
        trigger = cpt.createProgramTrigger(ptc.getFilePath(), ptc.getTextArea(), ptc.getExitStatus());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}
