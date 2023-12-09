package manager;

import actions.Action;
import actions.ProgramAction.CreateProgramAction;
import actions.ProgramAction.ProgramAction;
import controller.ProgramActionController;

/**
 *
 * @author Paolo
 */
public class ProgramActionManager extends ActionManager{
    
    public ProgramActionManager() {
        super("/actions/ProgramAction/ProgramAction.fxml");
    }
    
    @Override
    public Action createAction() {
        ProgramActionController pac = ((ProgramActionController)super.getController());
        CreateProgramAction cpa = new CreateProgramAction();
        ProgramAction pa = cpa.createProgramAction(pac.getFilePath(), pac.getTextArea());
        return pa;
    }
    
}