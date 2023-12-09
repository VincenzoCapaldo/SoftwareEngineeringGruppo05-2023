package manager.actions;

import model.actions.Action;
import model.actions.DeleteFileAction;
import controller.actions.DeleteFileActionController;
import manager.ActionManager;

/**
 *
 * @author Paolo
 */
public class DeleteFileActionManager extends ActionManager{
    
    public DeleteFileActionManager() {
        super("/view/actions/DeleteFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        DeleteFileActionController dfac = ((DeleteFileActionController)super.getController());
        DeleteFileAction dfa = new DeleteFileAction(dfac.getDirectoryPath(),dfac.getFileName());
        return dfa;
    }
}