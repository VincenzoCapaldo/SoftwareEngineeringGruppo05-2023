/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.ProgramTrigger;

import triggers.programTrigger.ProgramTrigger;
import triggers.programTrigger.CreateProgramTrigger;
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
public class CreateProgramTriggerTest {

    /**
     * Test of createProgramTrigger method, of class CreateProgramTrigger.
     */
    @Test
    public void testCreateProgramTrigger() {

        String path = "";
        String parameters = "";
        int expectedExitValue = 0;
        CreateProgramTrigger instance = new CreateProgramTrigger();
        boolean expResult = instance.createProgramTrigger(path, parameters, expectedExitValue) instanceof ProgramTrigger;
        boolean result = true;
        assertEquals(expResult, result);

    }
    
}
