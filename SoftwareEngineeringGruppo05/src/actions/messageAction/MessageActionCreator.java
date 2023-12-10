package actions.messageAction;

import actions.Action;
import actions.messageAction.MessageActionController;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class MessageActionCreator extends ActionCreator{
    
    public MessageActionCreator() {
        super("/actions/messageAction/MessageAction.fxml");
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