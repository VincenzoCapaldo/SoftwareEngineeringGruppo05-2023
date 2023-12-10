/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.dateTrigger;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Luca
 */
public class DateTriggerTest {
    
    private DateTrigger dt;
    
    @Before
    public void setUp(){
        dt = new DateTrigger(LocalDate.now());
    }
    
    /**
     * Test of isVerified method, of class DateTrigger.
     */
    @Test
    public void testIsVerified() {

        
        boolean expResult = false;
        boolean result = dt.isVerified();
        assertEquals(expResult, result);

    }

    /**
     * Test of isRepeated method, of class DateTrigger.
     */
    @Test
    public void testIsRepeated() {

        
        boolean expResult = false;
        boolean result = dt.isRepeated();
        assertEquals(expResult, result);

    }

    /**
     * Testiamo che il Trigger viene verificato quando la data attuale coincide con quella voluta
     */
    @Test
    public void testCheckTrigger1() {

        
        dt.checkTrigger();
        boolean expResult = true;
        boolean result = dt.isVerified();
        assertEquals(expResult, result);

    }
    
    
    /*      Bisognerebbe attendere un giorno per terminare questo test
    @Test
    public void testCheckTrigger2() {

        DateTrigger instance = new DateTrigger(LocalDate.now().plusDays(1));
        instance.checkTrigger();
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);

    }*/
   

    /**
     * Test of toString method, of class DateTrigger.
     */
    @Test
    public void testToString() {

        
        String expResult = "Date";
        String result = dt.toString();
        assertEquals(expResult, result);

    }

}
