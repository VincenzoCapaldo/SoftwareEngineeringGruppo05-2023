package manager.actions;

import model.actions.Action;
import model.actions.MessageAction;
import controller.actions.MessageActionController;
import manager.ActionManager;

/**
 *
 * @author Paolo
 */
public class MessageActionManager extends ActionManager{
    
    public MessageActionManager() {
        super("/view/actions/MessageAction.fxml");
    }
    
    @Override
    public Action createAction() {
        MessageActionController mac = ((MessageActionController)super.getController());
        MessageAction ma = new MessageAction(mac.getTextArea());
        ma.addObserver(mac);
        return ma;
    }
}