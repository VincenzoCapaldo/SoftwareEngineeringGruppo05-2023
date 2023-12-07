/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.DayOfWeekTrigger;

import java.time.DayOfWeek;
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
    

    /**
     * Test of isVerified method, of class DayOfWeekTrigger.
     */
    @Test
    public void testIsVerified() {

        DayOfWeekTrigger instance = new DayOfWeekTrigger(DayOfWeek.MONDAY,false);
        boolean expResult = false;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of isRepeated method, of class DayOfWeekTrigger.
     */
    @Test
    public void testIsRepeated() {
        
        DayOfWeekTrigger instance = new DayOfWeekTrigger(DayOfWeek.MONDAY,false);
        boolean expResult = false;
        boolean result = instance.isRepeated();
        assertEquals(expResult, result);

    }
    
}
