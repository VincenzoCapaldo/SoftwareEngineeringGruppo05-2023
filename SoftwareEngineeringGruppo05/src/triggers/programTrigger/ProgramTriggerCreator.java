package triggers.programTrigger;

import triggers.TriggerCreator;
import rule.Rule;
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
        String os = System.getProperty("os.name").toLowerCase();        
        if(os.contains("win")){
            trigger = new CreatorProgramTriggerWin(ptc.getFilePath(), ptc.getTextArea(), ptc.getExitStatus()).create();  //crea il trigger per Windows
        }else if(os.contains("nix") || os.contains("nux") || os.contains("mac")){
            trigger = new CreatorProgramTriggerUnix(ptc.getFilePath(), ptc.getTextArea(), ptc.getExitStatus()).create();  //crea il trigger per Unix-like (Linux, macOS, ecc.)
        }
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}
