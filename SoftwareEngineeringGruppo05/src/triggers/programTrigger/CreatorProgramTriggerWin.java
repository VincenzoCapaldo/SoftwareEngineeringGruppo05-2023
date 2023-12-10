package triggers.programTrigger;

/**
 *
 * @author Luca
 */
public class CreatorProgramTriggerWin extends CreatorProgramTrigger{

    public CreatorProgramTriggerWin(String path, String parameters, int expectedExitValue) {
        super(path, parameters, expectedExitValue);
    }

    @Override
    public ProgramTrigger create() {
        return new ProgramTriggerWin(this.getPath(), this.getParameters(), this.getExpectedExitValue()); //crea il trigger per Windows
    }
    
}
