package manager;

import actions.Action;
import actions.CopyFileAction.CopyFileAction;
import controller.CopyFileActionController;

/**
 *
 * @author Paolo
 */
public class CopyFileActionManager extends ActionManager{
    
    public CopyFileActionManager() {
        super("/actions/CopyFileAction/CopyFileAction.fxml");
    }

    @Override
    public Action createAction() {
        CopyFileActionController cfac = ((CopyFileActionController)super.getController());
        CopyFileAction cfa = new CopyFileAction(cfac.getFilePath(),cfac.getDirectoryPath());
        return cfa;
    }
    
}