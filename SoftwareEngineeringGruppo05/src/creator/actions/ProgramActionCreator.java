package creator.actions;

import model.actions.Action;
import model.actions.CreateProgramAction;
import model.actions.ProgramAction;
import controller.actions.ProgramActionController;

/**
 *
 * @author Paolo
 */
public class ProgramActionCreator extends ActionCreator{
    
    public ProgramActionCreator() {
        super("/view/actions/ProgramAction.fxml");
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