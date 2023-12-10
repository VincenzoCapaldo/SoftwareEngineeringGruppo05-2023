package triggers.dateTrigger;

import java.time.LocalDate;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Paolo
 */
public class DateTriggerTest {
    
    private DateTrigger dt;
    
    @Before
    public void setUp(){
        dt = new DateTrigger(LocalDate.now());
    }
    
    @Test
    public void testIsVerified() {
        boolean expResult = false;
        boolean result = dt.isVerified();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsRepeated() {
        boolean expResult = false;
        boolean result = dt.isRepeated();
        assertEquals(expResult, result);
    }

    // testiamo se è verificato se la data attuale coincide con quello richiesto
    @Test
    public void testCheckTrigger1() {
        dt.checkTrigger();
        boolean expResult = true;
        boolean result = dt.isVerified();
        assertEquals(expResult, result);
    }
    
    /* Si dovrebbe attendere un giorno per terminare questo test
    @Test
    public void testCheckTrigger2() {
        DateTrigger instance = new DateTrigger(LocalDate.now().plusDays(1));
        instance.checkTrigger();
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
    }*/
   
    @Test
    public void testToString() {
        String expResult = "Date";
        String result = dt.toString();
        assertEquals(expResult, result);
    }

}
