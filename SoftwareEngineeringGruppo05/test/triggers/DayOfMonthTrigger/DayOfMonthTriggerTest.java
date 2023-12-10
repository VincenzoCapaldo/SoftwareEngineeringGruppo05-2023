/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.DayOfMonthTrigger;

import java.time.LocalDate;
import triggers.dayOfMonthTrigger.DayOfMonthTrigger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    
    /**
     * Test of isVerified method, of class DayOfMonthTrigger.
     */
    @Test
    public void testIsVerified() {

        
        boolean expResult = false;
        boolean result = domt.isVerified();
        assertEquals(expResult, result);

    }

    /**
     * Test of isRepeated method, of class DayOfMonthTrigger.
     */
    @Test
    public void testIsRepeated() {

        boolean expResult = false;
        boolean result = domt.isRepeated();
        assertEquals(expResult, result);

    }
    
    //Testiamo se il Trigger è verificato quando il giorno del mese coincide con quello di oggi
    
    @Test
    public void testCheckTrigger1(){
    
        domt.checkTrigger();
        boolean expResult = true;
        boolean result = domt.isVerified();
        assertEquals(expResult, result);
        
    }

    
    /*       Bisognerebbe aspettare 1 giorno per verificare questo test
    @Test
    public void testCheckTrigger2() {

        DayOfMonthTrigger instance = new DayOfMonthTrigger(LocalDate.now().plusDays(1).getDayOfMonth(), false);
        domt.checkTrigger();
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);

    }
    
            Bisognerebbe aspettare 1 mese per verificare questo trigger
    
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
