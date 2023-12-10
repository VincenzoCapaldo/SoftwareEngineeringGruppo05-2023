package creator.actions;

import model.actions.Action;
import model.actions.MoveFileAction;
import controller.actions.MoveFileActionController;

/**
 *
 * @author Paolo
 */
public class MoveFileActionCreator extends ActionCreator{
    
    public MoveFileActionCreator() {
        super("/view/actions/MoveFileAction.fxml");
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