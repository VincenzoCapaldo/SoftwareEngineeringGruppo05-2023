package actions.writeFileAction;

import actions.Action;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class WriteFileActionCreator extends ActionCreator{
    
    public WriteFileActionCreator() {
        super("/actions/writeFileAction/WriteFileAction.fxml");
    }
    
    @Override
    public Action createAction() {
        WriteFileActionController wfac = ((WriteFileActionController)super.getController());
        WriteFileAction wfa = null;
        if(wfac.getCB().isSelected()){ //se è selezionata la checkbox
            wfa = new WriteFileAction(wfac.getFilePath(), wfac.getTextArea()); //crea l'azione
        }
        return wfa;
    }
    
}