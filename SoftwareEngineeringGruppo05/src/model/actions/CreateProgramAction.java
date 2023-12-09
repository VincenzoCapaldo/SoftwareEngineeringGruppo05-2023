/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.actions;

/**
 *
 * @author Luca
 */
public class CreateProgramAction {
    
    private CreatorProgramAction createProgramAction;
    
    public ProgramAction createProgramAction(String path, String parameters){
        
        String os = System.getProperty("os.name").toLowerCase();
        
        if(os.contains("win")){
            createProgramAction = new CreatorProgramActionWin(path, parameters);
        }else if(os.contains("nix") || os.contains("nux") || os.contains("mac")){
            createProgramAction = new CreatorProgramActionUnix(path, parameters);
        }
        
        return createProgramAction.create();
        
    }


    
}
