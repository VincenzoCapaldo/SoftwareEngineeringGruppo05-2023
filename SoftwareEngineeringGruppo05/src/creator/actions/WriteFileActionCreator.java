package creator.actions;

import model.actions.Action;
import model.actions.WriteFileAction;
import controller.actions.WriteFileActionController;

/**
 *
 * @author Paolo
 */
public class WriteFileActionCreator extends ActionCreator{
    
    public WriteFileActionCreator() {
        super("/view/actions/WriteFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        WriteFileActionController wfac = ((WriteFileActionController)super.getController());
        WriteFileAction wfa = new WriteFileAction(wfac.getFilePath(), wfac.getTextArea());
        return wfa;
    }
}