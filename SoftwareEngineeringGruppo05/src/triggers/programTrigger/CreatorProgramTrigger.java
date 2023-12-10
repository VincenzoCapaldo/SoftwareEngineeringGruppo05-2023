package triggers.programTrigger;

/**
 *
 * @author Luca
 */
public abstract class CreatorProgramTrigger {
    
    private String path; //path del programma da eseguire
    private String parameters; //eventuali parametri
    private int expectedExitValue; //exit value atteso

    public CreatorProgramTrigger(String path, String parameters, int expectedExitValue) {
        this.path = path;
        this.parameters = parameters;
        this.expectedExitValue = expectedExitValue;
    }
    
    public String getPath() {
        return path;
    }

    public String getParameters() {
        return parameters;
    }

    public int getExpectedExitValue() {
        return expectedExitValue;
    }
    
     public abstract ProgramTrigger create();
    
}
