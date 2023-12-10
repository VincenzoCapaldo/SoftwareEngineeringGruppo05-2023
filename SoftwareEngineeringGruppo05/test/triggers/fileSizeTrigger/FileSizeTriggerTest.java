/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.fileSizeTrigger;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class FileSizeTriggerTest {
    
    private FileSizeTrigger fst;
    private String directory;
    
    @Before
    public void setUp(){
        //percorso del file utilizzato per la prova della dimensione
        directory = System.getProperty("user.dir") + "/test/triggers/FileSizeTrigger/prova.txt";
        fst = new FileSizeTrigger(directory, 1);
    }
    
    /**
     * Test of isVerified method, of class FileSizeTrigger.
     */
    @Test
    public void testIsVerified() {

        
        boolean expResult = false;
        boolean result = fst.isVerified();
        assertEquals(expResult, result);

    }

    /**
     * Test of isRepeated method, of class FileSizeTrigger.
     */
    @Test
    public void testIsRepeated() {

        
        boolean expResult = false;
        boolean result = fst.isRepeated();
        assertEquals(expResult, result);

    }

    /**
     * Testiamo che il Trigger è verificato quando la dimensione del file supera quella indicata
     */
    @Test
    public void testCheckTrigger() {

        
        fst.checkTrigger();
        boolean expResult = true;
        boolean result = fst.isVerified();
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class FileSizeTrigger.
     */
    @Test
    public void testToString() {


        String expResult = "FileSize";
        String result = fst.toString();
        assertEquals(expResult, result);

    }
    
}
