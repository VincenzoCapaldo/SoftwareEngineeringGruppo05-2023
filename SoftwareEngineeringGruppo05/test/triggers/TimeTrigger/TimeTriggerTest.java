package triggers.TimeTrigger;

import triggers.timeTrigger.TimeTrigger;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.time.Duration;
import java.time.LocalTime;

/**
 *
 * @author Luca
 */
public class TimeTriggerTest {

    private TimeTrigger tt;
    
    @Before
    public void setUp(){
        tt = new TimeTrigger(0,0,false,Duration.ZERO);
    }
    
    @Test
    public void testIsVerified() {
        boolean expResult = false;
        boolean result = tt.isVerified();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsRepeated() {
        boolean expResult = false;
        boolean result = tt.isRepeated();
        assertEquals(expResult, result);
    }

    // testiamo se è verificato se l'orario attuale coincide con quello richiesto
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
    
    /* Si deve attendere un minuto per terminare questo test
    @Test
    public void testCheckTrigger2() {
        int hours = LocalTime.now().getHour();
        int minutes = LocalTime.now().getMinute()+1;
        TimeTrigger instance = new TimeTrigger(hours,minutes,false,Duration.ZERO);
        instance.checkTrigger();
        boolean result = instance.isVerified();
        boolean expResult = true;
        assertEquals(expResult,result);
    }*/
    
    /* Si dovrebbe attendere un giorno per terminare questo test
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
    
    @Test
    public void testToString() {
        String expResult = "Time";
        String result = tt.toString();
        assertEquals(expResult, result);
    }

}
