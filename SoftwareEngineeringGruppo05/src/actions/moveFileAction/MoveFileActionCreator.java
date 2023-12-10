package actions.moveFileAction;

import actions.Action;
import actions.moveFileAction.MoveFileActionController;
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
        if(mfac.getCB().isSelected()){
            mfa = new MoveFileAction(mfac.getFilePath(),mfac.getDirectoryPath());
        }
        return mfa;
    }
    
}