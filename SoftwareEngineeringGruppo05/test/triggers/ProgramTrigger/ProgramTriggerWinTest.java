/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.ProgramTrigger;

import triggers.programTrigger.ProgramTriggerWin;
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
public class ProgramTriggerWinTest {
    
    /**
     * Test of checkTrigger method, of class ProgramTriggerWin.
     */
    @Test
    public void testCheckTrigger() {

        String directory = System.getProperty("user.dir") + "/test/triggers/ProgramTrigger/test_program_win/program.jar";
        
        ProgramTriggerWin instance = new ProgramTriggerWin(directory,"12",0);
        instance.checkTrigger();
        boolean result = true;
        assertEquals(result,instance.isVerified());

    }
    
}
