package softwareengineeringgruppo05;

import actions.ActionCreator;
import actions.audioAction.AudioActionCreator;
import actions.copyFileAction.CopyFileActionCreator;
import actions.deleteFileAction.DeleteFileActionCreator;
import actions.messageAction.MessageActionCreator;
import actions.moveFileAction.MoveFileActionCreator;
import actions.programAction.ProgramActionCreator;
import actions.writeFileAction.WriteFileActionCreator;
import java.util.Map;

/**
 *
 * @author Paolo
 */
public class AvailableActions {
    
    //popola la mappa di actions in base alle azioni che si vogliono avere nel programma
    public static void createActionCreators(Map<String, ActionCreator> actions) {
        actions.put("Audio", new AudioActionCreator());
        actions.put("Message", new MessageActionCreator());
        actions.put("WriteFile", new WriteFileActionCreator());
        actions.put("CopyFile", new CopyFileActionCreator());
        actions.put("MoveFile", new MoveFileActionCreator());
        actions.put("DeleteFile", new DeleteFileActionCreator());
        actions.put("Program", new ProgramActionCreator());
    }

}
