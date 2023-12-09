/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.ProgramTrigger;

import model.triggers.ProgramTrigger;
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
public class ProgramTriggerTest {
    
    
    /**
     * Test of getProgramPath method, of class ProgramTrigger.
     */
    @Test
    public void testGetProgramPath() {

        ProgramTrigger instance = new ProgramTriggerImpl();
        String expResult = "";
        String result = instance.getProgramPath();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCommandLine method, of class ProgramTrigger.
     */
    @Test
    public void testGetCommandLine() {

        ProgramTrigger instance = new ProgramTriggerImpl();
        String expResult = "";
        String result = instance.getCommandLine();
        assertEquals(expResult, result);

    }

    /**
     * Test of getExpectedExitValue method, of class ProgramTrigger.
     */
    @Test
    public void testGetExpectedExitValue() {

        ProgramTrigger instance = new ProgramTriggerImpl();
        int expResult = 0;
        int result = instance.getExpectedExitValue();
        assertEquals(expResult, result);

    }


    /**
     * Test of isVerified method, of class ProgramTrigger.
     */
    @Test
    public void testIsVerified() {

        ProgramTrigger instance = new ProgramTriggerImpl();
        boolean expResult = false;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of setVerified method, of class ProgramTrigger.
     */
    @Test
    public void testSetVerified() {

        ProgramTrigger instance = new ProgramTriggerImpl();
        boolean verified = false;
        instance.setVerified(verified);
        boolean result = instance.isVerified();
        assertEquals(result, verified);
        
    }

    /**
     * Test of isRepeated method, of class ProgramTrigger.
     */
    @Test
    public void testIsRepeated() {

        ProgramTrigger instance = new ProgramTriggerImpl();
        boolean expResult = false;
        boolean result = instance.isRepeated();
        assertEquals(expResult, result);

    }

    /**
     * Test of checkTrigger method, of class ProgramTrigger.
     */
    @Test
    public void testCheckTrigger() {

        ProgramTrigger instance = new ProgramTriggerImpl();
        instance.checkTrigger();

    }

    public class ProgramTriggerImpl extends ProgramTrigger {

        public ProgramTriggerImpl() {
            super("", "", 0);
        }

        public void checkTrigger() {
            System.out.println("TestCheckTrigger");
        }
    }
    
}
