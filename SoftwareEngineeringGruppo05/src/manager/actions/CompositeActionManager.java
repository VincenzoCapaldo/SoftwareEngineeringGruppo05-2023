package manager.actions;

import model.actions.Action;
import manager.ActionManager;
import model.actions.CompositeAction;

/**
 *
 * @author Paolo
 */
public class CompositeActionManager extends ActionManager{
    
    public CompositeActionManager() {
        super("/view/actions/CompositeAction.fxml");
    }

    @Override
    public Action createAction() {
        //CompositeActionController cac = ((CompositeActionController)super.getController());
        CompositeAction ca = new CompositeAction();
        return ca;
    }
    
}