package actions.programAction;

import actions.Action;
import actions.programAction.CreateProgramAction;
import actions.programAction.ProgramAction;
import actions.programAction.ProgramActionController;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class ProgramActionCreator extends ActionCreator{
    
    public ProgramActionCreator() {
        super("/actions/programAction/ProgramAction.fxml");
    }
    
    @Override
    public Action createAction() {
        ProgramActionController pac = ((ProgramActionController)super.getController());
        ProgramAction pa = null;
        if(pac.getCB().isSelected()){
            CreateProgramAction cpa = new CreateProgramAction();
            pa = cpa.createProgramAction(pac.getFilePath(), pac.getTextArea());
        }
        return pa;
    }
    
}