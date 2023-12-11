package triggers.FileSizeTriggerTest;

import triggers.fileSizeTrigger.FileSizeTrigger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paolo
 */
public class FileSizeTriggerTest {
    
    private FileSizeTrigger fst;
    private String directory;

    @Before
    public void setUp(){
        directory = "test/triggers/FileSizeTriggerTest/prova.txt";
        fst = new FileSizeTrigger(directory, 1);
    }

    @Test
    public void testIsVerified() {
        boolean expResult = false;
        boolean result = fst.isVerified();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsRepeated() {
        boolean expResult = false;
        boolean result = fst.isRepeated();
        assertEquals(expResult, result);
    }

    // testiamo che il Trigger è verificato quando la dimensione del file supera quella indicata
    @Test
    public void testCheckTrigger() {
        fst.checkTrigger();
        boolean expResult = true;
        boolean result = fst.isVerified();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        String expResult = "FileSize";
        String result = fst.toString();
        assertEquals(expResult, result);
    }

}