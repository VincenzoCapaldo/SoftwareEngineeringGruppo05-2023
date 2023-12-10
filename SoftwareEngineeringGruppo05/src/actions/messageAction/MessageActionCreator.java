package actions.messageAction;

import actions.Action;
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
        if (mac.getCB().isSelected()){ //se è selezionata la checkbox
            ma = new MessageAction(mac.getTextArea());  //crea l'azione
            ma.addObserver(mac); //il controller osserva l'azione
        }
        return ma;
    }
}