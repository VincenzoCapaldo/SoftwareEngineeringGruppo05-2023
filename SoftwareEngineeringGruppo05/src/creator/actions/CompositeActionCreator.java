package creator.actions;

import model.actions.Action;
import model.actions.CompositeAction;

/**
 *
 * @author Paolo
 */
public class CompositeActionCreator extends ActionCreator{
    
    public CompositeActionCreator() {
        super("/view/actions/CompositeAction.fxml");
    }

    @Override
    public Action createAction() {
        //CompositeActionController cac = ((CompositeActionController)super.getController());
        CompositeAction ca = new CompositeAction();
        return ca;
    }
    
}