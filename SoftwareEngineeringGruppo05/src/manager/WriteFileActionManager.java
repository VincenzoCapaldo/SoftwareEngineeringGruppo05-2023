package manager;

import actions.Action;
import actions.WriteFileAction.WriteFileAction;
import controller.WriteFileActionController;

/**
 *
 * @author Paolo
 */
public class WriteFileActionManager extends ActionManager{
    
    public WriteFileActionManager() {
        super("/actions/WriteFileAction/WriteFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        WriteFileActionController wfac = ((WriteFileActionController)super.getController());
        WriteFileAction wfa = new WriteFileAction(wfac.getFilePath(), wfac.getTextArea());
        return wfa;
    }
}