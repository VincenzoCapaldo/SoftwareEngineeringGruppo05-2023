package triggers.DayOfMonthTrigger;

import java.time.LocalDate;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import triggers.dayOfMonthTrigger.DayOfMonthTrigger;

/**
 *
 * @author Luca
 */
public class DayOfMonthTriggerTest {
    
    private DayOfMonthTrigger domt;
    
    @Before
    public void setUp(){
        domt = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(),false); //Imposto la classe al giorno attuale
    }
    
    @Test
    public void testIsVerified() {
        boolean expResult = false;
        boolean result = domt.isVerified();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsRepeated() {
        boolean expResult = false;
        boolean result = domt.isRepeated();
        assertEquals(expResult, result);
    }
    
    // testiamo se è verificato se il giorno del mese attuale coincide con quello richiesto
    @Test
    public void testCheckTrigger1(){
        domt.checkTrigger();
        boolean expResult = true;
        boolean result = domt.isVerified();
        assertEquals(expResult, result);
        
    }

    /* Si dovrebbe attendere un giorno per terminare questo test
    @Test
    public void testCheckTrigger2() {
        DayOfMonthTrigger instance = new DayOfMonthTrigger(LocalDate.now().plusDays(1).getDayOfMonth(), false);
        domt.checkTrigger();
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
    }*/
    
    /* Si dovrebbe attendere un mese per terminare questo test
    @Test
    public void testCheckTrigger3() {
        DayOfMonthTrigger instance = new DayOfMonthTrigger(LocalDate.now().minusDays(1).getDayOfMonth(), false);
        domt.checkTrigger();
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
    }*/

    @Test
    public void testToString() {
        String expResult = "DayOfMonth";
        String result = domt.toString();
        assertEquals(expResult, result);
    }
 
}
