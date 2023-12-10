package creator.actions;

import model.actions.Action;
import model.actions.CopyFileAction;
import controller.actions.CopyFileActionController;

/**
 *
 * @author Paolo
 */
public class CopyFileActionCreator extends ActionCreator{
    
    public CopyFileActionCreator() {
        super("/view/actions/CopyFileAction.fxml");
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