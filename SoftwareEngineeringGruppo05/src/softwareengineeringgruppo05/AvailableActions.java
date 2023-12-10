package softwareengineeringgruppo05;

import creator.actions.ActionCreator;
import creator.actions.AudioActionCreator;
import creator.actions.CopyFileActionCreator;
import creator.actions.DeleteFileActionCreator;
import creator.actions.MessageActionCreator;
import creator.actions.MoveFileActionCreator;
import creator.actions.ProgramActionCreator;
import creator.actions.WriteFileActionCreator;
import creator.actions.CompositeActionCreator;
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
        actions.put("Composite", new CompositeActionCreator());
    }

}
