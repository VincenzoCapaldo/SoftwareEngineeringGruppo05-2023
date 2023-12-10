package actions.moveFileAction;

import actions.Action;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class MoveFileActionCreator extends ActionCreator{
    
    public MoveFileActionCreator() {
        super("/actions/moveFileAction/MoveFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        MoveFileActionController mfac = ((MoveFileActionController)super.getController());
        MoveFileAction mfa = null;
        if(mfac.getCB().isSelected()){ //se è selezionata la checkbox
            mfa = new MoveFileAction(mfac.getFilePath(),mfac.getDirectoryPath()); //crea l'azione
        }
        return mfa;
    }
    
}