package actions.copyFileAction;

import actions.Action;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class CopyFileActionCreator extends ActionCreator{
    
    public CopyFileActionCreator() {
        super("/actions/copyFileAction/CopyFileAction.fxml");
    }

    @Override
    public Action createAction() {
        CopyFileActionController cfac = ((CopyFileActionController)super.getController());
        CopyFileAction cfa = null;
        if(cfac.getCB().isSelected()){ //se è selezionata la checkbox
            cfa = new CopyFileAction(cfac.getFilePath(),cfac.getDirectoryPath()); //crea l'azione
        }
        return cfa;
    }
    
}