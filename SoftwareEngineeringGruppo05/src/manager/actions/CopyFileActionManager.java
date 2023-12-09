package manager.actions;

import model.actions.Action;
import model.actions.CopyFileAction;
import controller.actions.CopyFileActionController;
import manager.ActionManager;

/**
 *
 * @author Paolo
 */
public class CopyFileActionManager extends ActionManager{
    
    public CopyFileActionManager() {
        super("/view/actions/CopyFileAction.fxml");
    }

    @Override
    public Action createAction() {
        CopyFileActionController cfac = ((CopyFileActionController)super.getController());
        CopyFileAction cfa = new CopyFileAction(cfac.getFilePath(),cfac.getDirectoryPath());
        return cfa;
    }
    
}