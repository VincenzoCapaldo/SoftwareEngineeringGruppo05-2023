package actions.writeFileAction;

import actions.Action;
import actions.writeFileAction.WriteFileActionController;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class WriteFileActionCreator extends ActionCreator{
    
    public WriteFileActionCreator() {
        super("/actions/writeFileAction/WriteFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        WriteFileActionController wfac = ((WriteFileActionController)super.getController());
        WriteFileAction wfa = null;
        if(wfac.getCB().isSelected()){
            wfa = new WriteFileAction(wfac.getFilePath(), wfac.getTextArea());
        }
        return wfa;
    }
    
}