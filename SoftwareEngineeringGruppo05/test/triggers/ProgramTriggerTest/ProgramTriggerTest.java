package triggers.ProgramTriggerTest;

import triggers.programTrigger.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class ProgramTriggerTest {
    
    private ProgramTrigger pt;
    private String directory;
    
    @Before
    public void setUp(){
        //percorso del file che viene eseguito da ProgramTrigger
        directory = "test/triggers/ProgramTrigger/program.jar";      
        pt = (new CreateProgramTrigger()).createProgramTrigger(directory,"12",1);
    }
    
    @Test
    public void testGetProgramPath() {
        String expResult = directory;
        String result = pt.getProgramPath();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCommandLine() {
    String expResult = "12";
        String result = pt.getCommandLine();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetExpectedExitValue() {
        int expResult = 1;
        int result = pt.getExpectedExitValue();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsVerified() {
        boolean expResult = false;
        boolean result = pt.isVerified();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetVerified() {
        boolean verified = false;
        pt.setVerified(verified);
        boolean result = pt.isVerified();
        assertEquals(result, verified);    
    }

    @Test
    public void testIsRepeated() {
        boolean expResult = false;
        boolean result = pt.isRepeated();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckTrigger() {
        pt.checkTrigger();
        boolean expResult = true;
        boolean result = pt.isVerified();
        assertEquals(expResult, result);    
    }
    
    @Test
    public void testToString() {
        String expResult = "Program";
        String result = pt.toString();
        assertEquals(expResult, result);
    }

    
    // Classe utilizzata per la creazione della classe ProgramTrigger che varia in base al sistema operativo
    public class CreateProgramTrigger {
    
    private CreatorProgramTrigger createProgramTrigger;
    
    public ProgramTrigger createProgramTrigger(String path, String parameters, int expectedExitValue){
    
        String os = System.getProperty("os.name").toLowerCase();
        
        if(os.contains("win")){
            createProgramTrigger = new CreatorProgramTriggerWin(path, parameters, expectedExitValue);
        }else if(os.contains("nix") || os.contains("nux") || os.contains("mac")){
            createProgramTrigger = new CreatorProgramTriggerUnix(path, parameters, expectedExitValue);
        }
            
        return createProgramTrigger.create();
    }
    
}
    
}
