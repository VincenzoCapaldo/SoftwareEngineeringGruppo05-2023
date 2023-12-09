/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.ProgramTrigger;

import model.triggers.ProgramTrigger;
import model.triggers.CreatorProgramTrigger;
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
public class CreatorProgramTriggerTest {
    
    /**
     * Test of create method, of class CreatorProgramTrigger.
     */
    @Test
    public void testCreate() {

        CreatorProgramTrigger instance = new CreatorProgramTriggerImpl();
        ProgramTrigger expResult = null;
        ProgramTrigger result = instance.create();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPath method, of class CreatorProgramTrigger.
     */
    @Test
    public void testGetPath() {

        CreatorProgramTrigger instance = new CreatorProgramTriggerImpl();
        String expResult = "";
        String result = instance.getPath();
        assertEquals(expResult, result);

    }

    /**
     * Test of getParameters method, of class CreatorProgramTrigger.
     */
    @Test
    public void testGetParameters() {

        CreatorProgramTrigger instance = new CreatorProgramTriggerImpl();
        String expResult = "";
        String result = instance.getParameters();
        assertEquals(expResult, result);

    }

    /**
     * Test of getExpectedExitValue method, of class CreatorProgramTrigger.
     */
    @Test
    public void testGetExpectedExitValue() {

        CreatorProgramTrigger instance = new CreatorProgramTriggerImpl();
        int expResult = 0;
        int result = instance.getExpectedExitValue();
        assertEquals(expResult, result);

    }

    public class CreatorProgramTriggerImpl extends CreatorProgramTrigger {

        public CreatorProgramTriggerImpl() {
            super("", "", 0);
        }

        public ProgramTrigger create() {
            return null;
        }
    }
    
}
