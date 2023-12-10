package triggers.programTrigger;

/**
 *
 * @author Luca
 */
public class CreatorProgramTriggerUnix extends CreatorProgramTrigger{

    public CreatorProgramTriggerUnix(String path, String parameters, int expectedExitValue) {
        super(path, parameters, expectedExitValue);
    }

    @Override
    public ProgramTrigger create() {
        return new ProgramTriggerUnix(this.getPath(), this.getParameters(), this.getExpectedExitValue()); //crea il trigger per Unix-like (Linux, macOS, ecc.)
    }
    
}
