package triggers.DayOfWeekTriggerTest;

import triggers.dayOfWeekTrigger.DayOfWeekTrigger;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

/**
 *
 * @author Luca
 */
public class DayOfWeekTriggerTest {
    
    private DayOfWeekTrigger dowt;

    @Before
    public void setUp(){
        dowt = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(),false); // Imposto il giorno della settimana a quello attuale
    }
    
    @Test
    public void testIsVerified() {
        boolean expResult = false;
        boolean result = dowt.isVerified();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsRepeated() {    
        boolean expResult = false;
        boolean result = dowt.isRepeated();
        assertEquals(expResult, result);
    }
    
    // testiamo se è verificato se il giorno della settimana attuale coincide con quello richiesto
    @Test
    public void testCheckTrigger1(){
        dowt.checkTrigger();
        boolean expResult = true;
        boolean result = dowt.isVerified();
        assertEquals(expResult, result);
    }
      
    /* Si dovrebbe attendere un giorno per terminare questo test
    @Test
    public void testCheckTrigger2(){
        DayOfWeekTrigger instance = new DayOfWeekTrigger(LocalDate.now().plusDays(1).getDayOfWeek(),false);
        instance.checkTrigger();
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);       
    }*/
    
    /* Si dovrebbe attendere una settimana per terminare questo test
    @Test
    public void testCheckTrigger2(){
        DayOfWeekTrigger instance = new DayOfWeekTrigger(LocalDate.now().minusDays(1).getDayOfWeek(),false);
        instance.checkTrigger();
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
    }*/
    
    @Test
    public void testToString() {
        String expResult = "DayOfWeek";
        String result = dowt.toString();
        assertEquals(expResult, result);
    }
    
}
