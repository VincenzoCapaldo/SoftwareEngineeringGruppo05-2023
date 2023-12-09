package manager.actions;

import model.actions.Action;
import model.actions.MoveFileAction;
import controller.actions.MoveFileActionController;
import manager.ActionManager;

/**
 *
 * @author Paolo
 */
public class MoveFileActionManager extends ActionManager{
    
    public MoveFileActionManager() {
        super("/view/actions/MoveFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        MoveFileActionController mfac = ((MoveFileActionController)super.getController());
        MoveFileAction mfa = new MoveFileAction(mfac.getFilePath(), mfac.getDirectoryPath());
        return mfa;
    }
}