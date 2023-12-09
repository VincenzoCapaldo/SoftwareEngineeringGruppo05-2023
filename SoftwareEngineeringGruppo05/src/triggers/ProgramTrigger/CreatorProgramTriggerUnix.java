/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triggers.ProgramTrigger;

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
        return new ProgramTriggerUnix(this.getPath(), this.getParameters(), this.getExpectedExitValue());
    }
    
}
