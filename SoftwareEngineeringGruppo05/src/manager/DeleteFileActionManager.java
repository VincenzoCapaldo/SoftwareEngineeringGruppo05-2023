package manager;

import actions.Action;
import actions.DeleteFileAction.DeleteFileAction;
import controller.DeleteFileActionController;

/**
 *
 * @author Paolo
 */
public class DeleteFileActionManager extends ActionManager{
    
    public DeleteFileActionManager() {
        super("/actions/DeleteFileAction/DeleteFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        DeleteFileActionController dfac = ((DeleteFileActionController)super.getController());
        DeleteFileAction dfa = new DeleteFileAction(dfac.getDirectoryPath(),dfac.getFileName());
        return dfa;
    }
}