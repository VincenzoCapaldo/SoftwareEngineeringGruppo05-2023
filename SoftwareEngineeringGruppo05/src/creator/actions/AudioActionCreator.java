package creator.actions;

import model.actions.Action;
import model.actions.AudioAction;
import controller.actions.AudioActionController;

/**
 *
 * @author Paolo
 */
public class AudioActionCreator extends ActionCreator{
    
    public AudioActionCreator() {
        super("/view/actions/AudioAction.fxml");
    }

    @Override
    public Action createAction() {
        AudioActionController aac = ((AudioActionController)super.getController());
        AudioAction aa = new AudioAction(aac.getFilePath());
        return aa;
    }
    
}