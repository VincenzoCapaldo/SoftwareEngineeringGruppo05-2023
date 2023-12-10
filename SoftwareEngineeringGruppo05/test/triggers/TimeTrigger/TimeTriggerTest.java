package triggers.TimeTrigger;

import triggers.timeTrigger.TimeTrigger;
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
    public void testCheckTrigger1() {

        int hours = LocalTime.now().getHour();
        int minutes = LocalTime.now().getMinute();
        TimeTrigger instance = new TimeTrigger(hours,minutes,false,Duration.ZERO);
        instance.checkTrigger();
        boolean result = instance.isVerified();
        boolean expResult = true;
        assertEquals(expResult,result);
        
    }
    
    @Test
    public void testCheckTrigger2() {

        int hours = LocalTime.now().getHour();
        int minutes = LocalTime.now().getMinute()+1;
        TimeTrigger instance = new TimeTrigger(hours,minutes,false,Duration.ZERO);
        instance.checkTrigger();
        boolean result = instance.isVerified();
        boolean expResult = true;
        assertEquals(expResult,result);
        
    }
    
    /*          Non si ha a disposizione abbatanza tempo per attendere la fine del test
    @Test
    public void testCheckTrigger3() {

        int hours = LocalTime.now().getHour();
        int minutes = LocalTime.now().getMinute()-1;
        TimeTrigger instance = new TimeTrigger(hours,minutes,false,Duration.ZERO);
        instance.checkTrigger();
        boolean result = instance.isVerified();
        boolean expResult = true;
        assertEquals(expResult,result);
        
    }*/

}
