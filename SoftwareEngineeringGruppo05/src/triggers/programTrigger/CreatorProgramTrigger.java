/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triggers.programTrigger;

/**
 *
 * @author Luca
 */
public abstract class CreatorProgramTrigger {
    
    private String path;
    private String parameters;
    private int expectedExitValue;

    public CreatorProgramTrigger(String path, String parameters, int expectedExitValue) {
        this.path = path;
        this.parameters = parameters;
        this.expectedExitValue = expectedExitValue;
    }
    
    public abstract ProgramTrigger create();

    public String getPath() {
        return path;
    }

    public String getParameters() {
        return parameters;
    }

    public int getExpectedExitValue() {
        return expectedExitValue;
    }
    
    
    
}
