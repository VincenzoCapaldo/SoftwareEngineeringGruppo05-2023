package manager.actions;

import model.actions.Action;
import model.actions.CreateProgramAction;
import model.actions.ProgramAction;
import controller.actions.ProgramActionController;
import manager.ActionManager;

/**
 *
 * @author Paolo
 */
public class ProgramActionManager extends ActionManager{
    
    public ProgramActionManager() {
        super("/view/actions/ProgramAction.fxml");
    }
    
    @Override
    public Action createAction() {
        ProgramActionController pac = ((ProgramActionController)super.getController());
        CreateProgramAction cpa = new CreateProgramAction();
        ProgramAction pa = cpa.createProgramAction(pac.getFilePath(), pac.getTextArea());
        return pa;
    }
    
}