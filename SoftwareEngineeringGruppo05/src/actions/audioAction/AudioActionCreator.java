package actions.audioAction;

import actions.Action;
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
        if (aac.getCB().isSelected()){ //se è selezionata la checkbox
            aa = new AudioAction(aac.getFilePath()); //crea l'azione
        }
        return aa;
    }    
    
}