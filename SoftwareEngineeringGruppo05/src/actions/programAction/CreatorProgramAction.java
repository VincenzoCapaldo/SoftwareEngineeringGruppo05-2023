package actions.programAction;

/**
 *
 * @author Luca
 */
public abstract class CreatorProgramAction{
    
    private String path; //path del programma da eseguire
    private String parameters; //eventuali parametri

    public CreatorProgramAction(String path, String parameters) {
        this.path = path;
        this.parameters = parameters;
    }

    public String getPath() {
        return path;
    }

    public String getParameters() {
        return parameters;
    }
    
    public abstract ProgramAction create();
        
}