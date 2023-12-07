/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.DayOfMonthTrigger;

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
public class DayOfMonthTriggerTest {
    

    /**
     * Test of isVerified method, of class DayOfMonthTrigger.
     */
    @Test
    public void testIsVerified() {

        DayOfMonthTrigger instance = new DayOfMonthTrigger(13,false);
        boolean expResult = false;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);

    }

    /**
     * Test of isRepeated method, of class DayOfMonthTrigger.
     */
    @Test
    public void testIsRepeated() {

        DayOfMonthTrigger instance = new DayOfMonthTrigger(13,false);
        boolean expResult = false;
        boolean result = instance.isRepeated();
        assertEquals(expResult, result);

    }

 
}
