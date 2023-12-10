package creator.actions;

import model.actions.Action;
import model.actions.MessageAction;
import controller.actions.MessageActionController;

/**
 *
 * @author Paolo
 */
public class MessageActionCreator extends ActionCreator{
    
    public MessageActionCreator() {
        super("/view/actions/MessageAction.fxml");
    }
    
    @Override
    public Action createAction() {
        MessageActionController mac = ((MessageActionController)super.getController());
        MessageAction ma = null;
        if (mac.getCB().isSelected()){
            ma = new MessageAction(mac.getTextArea());
            ma.addObserver(mac);
        }
        return ma;
    }
}