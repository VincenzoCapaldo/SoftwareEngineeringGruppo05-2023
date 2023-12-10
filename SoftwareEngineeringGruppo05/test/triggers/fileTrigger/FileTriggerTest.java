/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
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
        // Cartella in cui è contenuto il file usato per il test
        directory = System.getProperty("user.dir") + "/test/triggers/FileTrigger";
        
        ft = new FileTrigger(directory,"prova.txt");
    }
    
    /**
     * Test of isVerified method, of class FileTrigger.
     */
    @Test
    public void testIsVerified() {

        
        boolean expResult = false;
        boolean result = ft.isVerified();
        assertEquals(expResult, result);

    }

    /**
     * Test of isRepeated method, of class FileTrigger.
     */
    @Test
    public void testIsRepeated() {

        
        boolean expResult = false;
        boolean result = ft.isRepeated();
        assertEquals(expResult, result);

    }

    /**
     * Testiamo che il Trigger è verificato quando viene creato il file nella directory indicata
     */
    @Test
    public void testCheckTrigger() {

      
        ft.checkTrigger();
        boolean expResult = true;
        boolean result = ft.isVerified();
        assertEquals(expResult, result);
        

    }

    /**
     * Test of toString method, of class FileTrigger.
     */
    @Test
    public void testToString() {

        FileTrigger instance = new FileTrigger("","");
        String expResult = "File";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
}
