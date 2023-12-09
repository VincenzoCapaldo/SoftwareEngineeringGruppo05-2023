/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.actions;

/**
 *
 * @author Luca
 */
public abstract class CreatorProgramAction{
    
    private String path;
    private String parameters;

    public CreatorProgramAction(String path, String parameters) {
        this.path = path;
        this.parameters = parameters;
    }

    public abstract ProgramAction create();

    public String getPath() {
        return path;
    }

    public String getParameters() {
        return parameters;
    }
    
    
    
}
