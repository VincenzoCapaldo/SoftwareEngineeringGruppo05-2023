package manager;

import actions.Action;
import actions.MoveFileAction.MoveFileAction;
import controller.MoveFileActionController;

/**
 *
 * @author Paolo
 */
public class MoveFileActionManager extends ActionManager{
    
    public MoveFileActionManager() {
        super("/actions/MoveFileAction/MoveFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        MoveFileActionController mfac = ((MoveFileActionController)super.getController());
        MoveFileAction mfa = new MoveFileAction(mfac.getFilePath(), mfac.getDirectoryPath());
        return mfa;
    }
}