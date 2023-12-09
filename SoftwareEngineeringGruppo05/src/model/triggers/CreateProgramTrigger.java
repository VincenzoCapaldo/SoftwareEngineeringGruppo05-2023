/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.triggers;

/**
 *
 * @author Luca
 */
public class CreateProgramTrigger {
    
    private CreatorProgramTrigger createProgramTrigger;
    
    public ProgramTrigger createProgramTrigger(String path, String parameters, int expectedExitValue){
    
        String os = System.getProperty("os.name").toLowerCase();
        
        if(os.contains("win")){
            createProgramTrigger = new CreatorProgramTriggerWin(path, parameters, expectedExitValue);
        }else if(os.contains("nix") || os.contains("nux") || os.contains("mac")){
            createProgramTrigger = new CreatorProgramTriggerUnix(path, parameters, expectedExitValue);
        }
        
        
        return createProgramTrigger.create();
    }
    
}
