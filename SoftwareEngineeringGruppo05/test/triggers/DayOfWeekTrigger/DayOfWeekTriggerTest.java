/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.DayOfWeekTrigger;

import triggers.dayOfWeekTrigger.DayOfWeekTrigger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class DayOfWeekTriggerTest {
    
    private DayOfWeekTrigger dowt;

    @Before
    public void setUp(){
        dowt = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(),false);
    }
    
    /**
     * Test of isVerified method, of class DayOfWeekTrigger.
     */
    @Test
    public void testIsVerified() {

        boolean expResult = false;
        boolean result = dowt.isVerified();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of isRepeated method, of class DayOfWeekTrigger.
     */
    @Test
    public void testIsRepeated() {
        
        boolean expResult = false;
        boolean result = dowt.isRepeated();
        assertEquals(expResult, result);

    }
    
    @Test
    public void testCheckTrigger1(){
    
        
        dowt.checkTrigger();
        boolean expResult = true;
        boolean result = dowt.isVerified();
        assertEquals(expResult, result);
        
    }
    
    
    /*      Bisognerebbe attendere un giorno per verificare questo test
    @Test
    public void testCheckTrigger2(){
    
        DayOfWeekTrigger instance = new DayOfWeekTrigger(LocalDate.now().plusDays(1).getDayOfWeek(),false);
        instance.checkTrigger();
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
        
    }*/
    
    /*      Bisognerebbe attendere 7 giorni per verificare questo test
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
