package creator.actions;

import model.actions.Action;
import model.actions.DeleteFileAction;
import controller.actions.DeleteFileActionController;

/**
 *
 * @author Paolo
 */
public class DeleteFileActionCreator extends ActionCreator{
    
    public DeleteFileActionCreator() {
        super("/view/actions/DeleteFileAction.fxml");
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