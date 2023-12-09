package manager.actions;

import model.actions.Action;
import model.actions.AudioAction;
import controller.actions.AudioActionController;
import manager.ActionManager;

/**
 *
 * @author Paolo
 */
public class AudioActionManager extends ActionManager{
    
    public AudioActionManager() {
        super("/view/actions/AudioAction.fxml");
    }

    @Override
    public Action createAction() {
        AudioActionController aac = ((AudioActionController)super.getController());
        AudioAction aa = new AudioAction(aac.getFilePath());
        return aa;
    }
    
}