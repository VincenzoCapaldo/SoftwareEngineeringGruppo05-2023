package manager;

import actions.Action;
import actions.MessageAction.MessageAction;
import controller.MessageActionController;

/**
 *
 * @author Paolo
 */
public class MessageActionManager extends ActionManager{
    
    public MessageActionManager() {
        super("/actions/MessageAction/MessageAction.fxml");
    }
    
    @Override
    public Action createAction() {
        MessageActionController mac = ((MessageActionController)super.getController());
        MessageAction ma = new MessageAction(mac.getTextArea());
        return ma;
    }
}