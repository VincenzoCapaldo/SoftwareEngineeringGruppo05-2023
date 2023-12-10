package softwareengineeringgruppo05;

import triggers.TriggerCreator;
import triggers.dateTrigger.DateTriggerCreator;
import triggers.dayOfMonthTrigger.DayOfMonthTriggerCreator;
import triggers.dayOfWeekTrigger.DayOfWeekTriggerCreator;
import triggers.fileSizeTrigger.FileSizeTriggerCreator;
import triggers.fileTrigger.FileTriggerCreator;
import triggers.programTrigger.ProgramTriggerCreator;
import triggers.timeTrigger.TimeTriggerCreator;
import java.util.Map;

/**
 *
 * @author Paolo
 */
public class AvailableTriggers {
    
    //popola la mappa di triggers in base ai triggers che si vogliono avere nel programma
    public static void createTriggerCreators(Map<String, TriggerCreator> triggers) {
        triggers.put("Time", new TimeTriggerCreator());
        triggers.put("DayOfWeek", new DayOfWeekTriggerCreator());
        triggers.put("DayOfMonth", new DayOfMonthTriggerCreator());
        triggers.put("Date", new DateTriggerCreator());
        triggers.put("File", new FileTriggerCreator());
        triggers.put("FileSize", new FileSizeTriggerCreator());
        triggers.put("Program", new ProgramTriggerCreator());
    }
    
}
