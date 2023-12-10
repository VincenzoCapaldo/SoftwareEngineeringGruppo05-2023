package actions.programAction;

/**
 *
 * @author Luca
 */
public class CreatorProgramActionWin extends CreatorProgramAction{

    public CreatorProgramActionWin(String path, String parameters) {
        super(path, parameters);
    }

    @Override
    public ProgramAction create() {
        return new ProgramActionWin(this.getPath(), this.getParameters()); //crea l'azione per Windows
    }
    
}
