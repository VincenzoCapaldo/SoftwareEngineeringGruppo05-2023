/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions.ProgramAction;

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
        return new ProgramActionUnix(this.getPath(),this.getParameters());
    }
    
}
