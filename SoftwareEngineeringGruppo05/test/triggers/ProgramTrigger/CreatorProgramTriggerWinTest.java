/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package triggers.ProgramTrigger;

import model.triggers.ProgramTrigger;
import model.triggers.CreatorProgramTriggerWin;
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
public class CreatorProgramTriggerWinTest {

    /**
     * Test of create method, of class CreatorProgramTriggerWin.
     */
    @Test
    public void testCreate() {

        CreatorProgramTriggerWin instance = new CreatorProgramTriggerWin("","",0);
        boolean expResult = instance.create() instanceof ProgramTrigger; 
        boolean result = true;
        assertEquals(expResult, result);

    }
    
}
