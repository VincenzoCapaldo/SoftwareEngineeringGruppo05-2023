/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers;

import java.time.LocalTime;
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
public class TimeTriggerTest {
    
    private TimeTrigger timetrigger;
    
    public TimeTriggerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of verify method, of class TimeTrigger.
     */
    @Test
    public void testVerify1() {
        System.out.print("Test case1 verify: ");
        
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute();
        TimeTrigger instance = new TimeTrigger(hour,minute);
        boolean expResult = true;
        boolean result = instance.verify();
        assertEquals(expResult, result);
        
        System.out.println("Pass\n");
    }
    
    @Test
    public void testVerify2() {
        System.out.print("Test case2 verify: ");
        
        int hour = LocalTime.now().getHour() - 1;
        int minute = LocalTime.now().getMinute();
        TimeTrigger instance = new TimeTrigger(hour, minute);
        boolean expResult = false;
        boolean result = instance.verify();
        assertEquals(expResult, result);
        
        System.out.println("Pass\n");
        
    }
    
    @Test
    public void testVerify3() {
        System.out.print("Test case2 verify: ");
        
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute() - 1;
        TimeTrigger instance = new TimeTrigger(hour, minute);
        boolean expResult = false;
        boolean result = instance.verify();
        assertEquals(expResult, result);
        
        System.out.println("Pass\n");
        
    }
    
}
