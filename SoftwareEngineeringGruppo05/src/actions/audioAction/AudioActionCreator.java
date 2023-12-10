package actions.audioAction;

import actions.Action;
import actions.audioAction.AudioActionController;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class AudioActionCreator extends ActionCreator{
    
    public AudioActionCreator() {
        super("/actions/audioAction/AudioAction.fxml");
    }

    @Override
    public Action createAction() {
        AudioActionController aac = ((AudioActionController)super.getController());
        AudioAction aa = null;
        if (aac.getCB().isSelected()){
            aa = new AudioAction(aac.getFilePath());
        }
        return aa;
    }    
    
}