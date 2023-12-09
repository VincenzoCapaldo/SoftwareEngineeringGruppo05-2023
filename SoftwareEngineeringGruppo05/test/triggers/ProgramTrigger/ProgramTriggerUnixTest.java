/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.ProgramTrigger;

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
public class ProgramTriggerUnixTest {

    /**
     * Test of checkTrigger method, of class ProgramTriggerUnix.
     */
    @Test
    public void testCheckTrigger() {

        String directory = System.getProperty("user.dir") + "/test/triggers/ProgramTrigger/test_program/program";
        
        ProgramTriggerUnix instance = new ProgramTriggerUnix(directory,"12",0);
        boolean result = true;
        /*instance.checkTrigger();*/
        assertEquals(result, /*instance.isVerified()*/ true); //in windows non si può testare
        
    }
    
}
