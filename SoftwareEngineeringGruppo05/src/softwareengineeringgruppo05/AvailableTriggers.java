package softwareengineeringgruppo05;

import creator.triggers.TriggerCreator;
import creator.triggers.DateTriggerCreator;
import creator.triggers.DayOfMonthTriggerCreator;
import creator.triggers.DayOfWeekTriggerCreator;
import creator.triggers.FileSizeTriggerCreator;
import creator.triggers.FileTriggerCreator;
import creator.triggers.ProgramTriggerCreator;
import creator.triggers.TimeTriggerCreator;
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
