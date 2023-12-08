package softwareengineeringgruppo05;

import actions.Action;
import actions.ActionController;
import actions.AudioAction.AudioAction;
import actions.AudioAction.AudioActionController;
import actions.CopyFileAction.CopyFileAction;
import actions.CopyFileAction.CopyFileActionController;
import actions.DeleteFileAction.DeleteFileAction;
import actions.DeleteFileAction.DeleteFileActionController;
import actions.MessageAction.MessageAction;
import actions.MessageAction.MessageActionController;
import actions.MoveFileAction.MoveFileAction;
import actions.MoveFileAction.MoveFileActionController;
import actions.ProgramAction.CreateProgramAction;
import actions.ProgramAction.ProgramActionController;
import actions.WriteFileAction.WriteFileAction;
import actions.WriteFileAction.WriteFileActionController;
import java.util.Map;

/**
 *
 * @author Paolo
 */
public class CreatorAction {

    private Map<String, ActionController> actionMap;
    private String choice;

    public CreatorAction(Map<String, ActionController> actionMap, String choice) {
        this.actionMap = actionMap;
        this.choice = choice;
    }
    
    public Action createAction (){
        Action action = null;
        
        switch (choice) {
            case "Audio":
                action = new AudioAction(((AudioActionController)actionMap.get("Audio")).getFilePath());
                break;
            case "Message":
                action = new MessageAction(((MessageActionController)actionMap.get("Message")).getTextArea());
                ((MessageAction)action).addObserver((MessageActionController)actionMap.get("Message"));
                break;
            case "WriteFile":
                action = new WriteFileAction(((WriteFileActionController)actionMap.get("WriteFile")).getFilePath(), ((WriteFileActionController)actionMap.get("WriteFile")).getTextArea());
                break;
            case "CopyFile":
                action = new CopyFileAction(((CopyFileActionController)actionMap.get("CopyFile")).getFilePath(), ((CopyFileActionController)actionMap.get("CopyFile")).getDirectoryPath());
                break;
            case "MoveFile":
                action = new MoveFileAction(((MoveFileActionController)actionMap.get("MoveFile")).getFilePath(), ((MoveFileActionController)actionMap.get("MoveFile")).getDirectoryPath());
                break;
            case "DeleteFile":
                action = new DeleteFileAction(((DeleteFileActionController)actionMap.get("DeleteFile")).getDirectoryPath(), ((DeleteFileActionController)actionMap.get("DeleteFile")).getFileName());
                break;
            case "ProgramFile":
                CreateProgramAction a = new CreateProgramAction();
                action = a.createProgramAction(((ProgramActionController)actionMap.get("Program")).getFilePath(), ((ProgramActionController)actionMap.get("Program")).getTextArea());
                break;
        }
        return action;
    }
    
}