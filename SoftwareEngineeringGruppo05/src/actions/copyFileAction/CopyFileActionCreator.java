package actions.copyFileAction;

import actions.Action;
import actions.copyFileAction.CopyFileActionController;
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
        if(cfac.getCB().isSelected()){
            cfa = new CopyFileAction(cfac.getFilePath(),cfac.getDirectoryPath());
        }
        return cfa;
    }
    
}