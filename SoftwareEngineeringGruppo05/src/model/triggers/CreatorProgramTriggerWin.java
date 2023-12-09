/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.triggers;

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
        return new ProgramTriggerWin(this.getPath(), this.getParameters(), this.getExpectedExitValue());
    }
    
}
