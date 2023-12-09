package manager.actions;

import model.actions.Action;
import model.actions.WriteFileAction;
import controller.actions.WriteFileActionController;
import manager.ActionManager;

/**
 *
 * @author Paolo
 */
public class WriteFileActionManager extends ActionManager{
    
    public WriteFileActionManager() {
        super("/view/actions/WriteFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        WriteFileActionController wfac = ((WriteFileActionController)super.getController());
        WriteFileAction wfa = new WriteFileAction(wfac.getFilePath(), wfac.getTextArea());
        return wfa;
    }
}