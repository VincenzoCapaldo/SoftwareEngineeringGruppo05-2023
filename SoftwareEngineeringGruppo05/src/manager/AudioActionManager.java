package manager;

import actions.Action;
import actions.AudioAction.AudioAction;
import controller.AudioActionController;

/**
 *
 * @author Paolo
 */
public class AudioActionManager extends ActionManager{
    
    public AudioActionManager() {
        super("/actions/AudioAction/AudioAction.fxml");
    }

    @Override
    public Action createAction() {
        AudioActionController aac = ((AudioActionController)super.getController());
        AudioAction aa = new AudioAction(aac.getFilePath());
        return aa;
    }
    
}