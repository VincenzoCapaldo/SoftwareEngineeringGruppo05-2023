package actions.deleteFileAction;

import actions.Action;
import actions.deleteFileAction.DeleteFileActionController;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class DeleteFileActionCreator extends ActionCreator{
    
    public DeleteFileActionCreator() {
        super("/actions/deleteFileAction/DeleteFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        DeleteFileActionController dfac = ((DeleteFileActionController)super.getController());
        DeleteFileAction dfa = null;
        if(dfac.getCB().isSelected()){
            dfa = new DeleteFileAction(dfac.getDirectoryPath(),dfac.getFileName());
        }
        return dfa;
    }
    
}