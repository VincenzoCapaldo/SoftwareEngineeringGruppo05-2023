package triggers.fileTrigger;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class FileTriggerTest {
    
    private FileTrigger ft;
    private String directory;
    
    @Before
    public void setUp(){
        directory = "/test/triggers/FileTrigger";
        ft = new FileTrigger(directory,"prova.txt");
    }
    
    @Test
    public void testIsVerified() {
        boolean expResult = false;
        boolean result = ft.isVerified();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsRepeated() {
        boolean expResult = false;
        boolean result = ft.isRepeated();
        assertEquals(expResult, result);
    }

    // testiamo che il Trigger è verificato quando esiste il file nella directory indicata
    @Test
    public void testCheckTrigger() {
        ft.checkTrigger();
        boolean expResult = true;
        boolean result = ft.isVerified();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        String expResult = "File";
        String result = ft.toString();
        assertEquals(expResult, result);
    }
    
}
