package actions.programAction;

import actions.Action;
import actions.ActionCreator;

/**
 *
 * @author Paolo
 */
public class ProgramActionCreator extends ActionCreator{
    
    public ProgramActionCreator() {
        super("/actions/programAction/ProgramAction.fxml");
    }
    
    @Override
    public Action createAction() {
        ProgramActionController pac = ((ProgramActionController)super.getController());
        ProgramAction pa = null;
        if(pac.getCB().isSelected()){ //se è selezionata la checkbox
            String os = System.getProperty("os.name").toLowerCase(); //ottiene il nome del sistema operativo     
            if(os.contains("win")){
                pa = new CreatorProgramActionWin(pac.getFilePath(), pac.getTextArea()).create(); //crea l'azione per Windows
            }else if(os.contains("nix") || os.contains("nux") || os.contains("mac")){
                pa = new CreatorProgramActionUnix(pac.getFilePath(), pac.getTextArea()).create(); //crea l'azione per Unix-like (Linux, macOS, ecc.)
            }
        }
        return pa;
    }   
    
}