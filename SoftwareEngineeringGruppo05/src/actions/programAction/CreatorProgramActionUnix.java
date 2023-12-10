package actions.programAction;

/**
 *
 * @author Luca
 */
public class CreatorProgramActionUnix extends CreatorProgramAction{

    public CreatorProgramActionUnix(String path, String parameters) {
        super(path, parameters);
    }

    @Override
    public ProgramAction create() {
        return new ProgramActionUnix(this.getPath(),this.getParameters()); //crea l'azione per Unix-like (Linux, macOS, ecc.)
    }
    
}
