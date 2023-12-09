package triggers.TimeTrigger;

import java.time.Duration;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalTime;

/**
 *
 * @author Luca
 */
public class TimeTriggerTest {

    /**
     * Test of isVerified method, of class TimeTrigger.
     */
    @Test
    public void testIsVerified() {


        TimeTrigger instance = new TimeTrigger(0,0,false,Duration.ZERO);
        boolean expResult = false;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);

    }

    /**
     * Test of isRepeated method, of class TimeTrigger.
     */
    @Test
    public void testIsRepeated() {


        TimeTrigger instance = new TimeTrigger(0,0,false,Duration.ZERO);
        boolean expResult = false;
        boolean result = instance.isRepeated();
        assertEquals(expResult, result);

    }

    /**
     * Test of checkTrigger method, of class TimeTrigger.
     */
    @Test
    public void testCheckTrigger() {

        int hours = LocalTime.now().getHour();
        int minutes = LocalTime.now().getMinute();
        TimeTrigger instance = new TimeTrigger(hours,minutes,false,Duration.ZERO);
        instance.checkTrigger();
        boolean result = instance.isVerified();
        boolean expResult = true;
        assertEquals(expResult,result);
        
    }

}
