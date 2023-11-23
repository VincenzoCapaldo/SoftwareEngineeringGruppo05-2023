package triggers;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalTime;

/**
 *
 * @author Luca
 */
public class TimeTriggerTest {
    
    private TimeTrigger timetrigger;
    
    /**
     * Test of verify method, of class TimeTrigger.
     */
    @Test
    public void testVerify1() {
        System.out.print("Test case1 verify: ");
        
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute();
        TimeTrigger instance = new TimeTrigger(hour,minute);
        boolean expResult = true;
        boolean result = instance.verify();
        assertEquals(expResult, result);
        
        System.out.println("Pass\n");
    }
    
    @Test
    public void testVerify2() {
        System.out.print("Test case2 verify: ");
        
        int hour = (LocalTime.now().getHour() + 1)%24;
        int minute = LocalTime.now().getMinute();
        TimeTrigger instance = new TimeTrigger(hour, minute);
        boolean expResult = false;
        boolean result = instance.verify();
        assertEquals(expResult, result);
        
        System.out.println("Pass\n");
        
    }
    
    @Test
    public void testVerify3() {
        System.out.print("Test case3 verify: ");
        
        int hour = LocalTime.now().getHour();
        int minute = (LocalTime.now().getMinute() + 1)%60;
        TimeTrigger instance = new TimeTrigger(hour, minute);
        boolean expResult = false;
        boolean result = instance.verify();
        assertEquals(expResult, result);
        
        System.out.println("Pass\n");
        
    }
    
}
