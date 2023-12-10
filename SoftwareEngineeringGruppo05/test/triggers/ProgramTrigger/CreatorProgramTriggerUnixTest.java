/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.ProgramTrigger;

import triggers.programTrigger.ProgramTrigger;
import triggers.programTrigger.CreatorProgramTriggerUnix;
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
public class CreatorProgramTriggerUnixTest {
    
    /**
     * Test of create method, of class CreatorProgramTriggerUnix.
     */
    @Test
    public void testCreate() {

        CreatorProgramTriggerUnix instance = new CreatorProgramTriggerUnix("","",0);
        boolean expResult = instance.create() instanceof ProgramTrigger; 
        boolean result = true;
        assertEquals(expResult, result);
    }
    
}
