package actions.deleteFileAction;

import actions.Action;
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
        if(dfac.getCB().isSelected()){ //se è selezionata la checkbox
            dfa = new DeleteFileAction(dfac.getDirectoryPath(),dfac.getFileName()); //crea l'azione
        }
        return dfa;
    }
    
}