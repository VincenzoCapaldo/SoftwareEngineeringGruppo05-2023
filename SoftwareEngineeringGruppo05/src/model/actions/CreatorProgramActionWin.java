/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.actions;

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
        return new ProgramActionWin(this.getPath(), this.getParameters());
    }
    
}
