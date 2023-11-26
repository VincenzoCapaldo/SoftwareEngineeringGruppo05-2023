package triggers.TimeTrigger;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalTime;

/**
 *
 * @author Luca
 */
public class TimeTriggerTest {
    
    private TimeTrigger timetrigger;
    
    /* test con ora e minuti correnti */
    @Test
    public void testVerify1() {
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute();
        TimeTrigger instance = new TimeTrigger(hour,minute);
        boolean expResult = true;
        boolean result = instance.verify();
        assertEquals(expResult, result);
    }
    
    /* test con ora diversa da quella corrente */
    @Test
    public void testVerify2() {
        int hour = (LocalTime.now().getHour() + 1)%24;
        int minute = LocalTime.now().getMinute();
        TimeTrigger instance = new TimeTrigger(hour, minute);
        boolean expResult = false;
        boolean result = instance.verify();
        assertEquals(expResult, result);
    }
    
    /* test con minuti diversi da quelli correnti */
    @Test
    public void testVerify3() {
        int hour = LocalTime.now().getHour();
        int minute = (LocalTime.now().getMinute() + 1)%60;
        TimeTrigger instance = new TimeTrigger(hour, minute);
        boolean expResult = false;
        boolean result = instance.verify();
        assertEquals(expResult, result);
    }
    
}
