/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package actions.ProgramAction;


import actions.programAction.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class ProgramActionTest {
    
    
    private ProgramAction pa;
    private String directory;
    private String filePath;

    @Before
    public void setUp() {
        
        // percorso del file che deve essere creato dal test ed eliminato dal programma avviato da Program Action
        filePath  = System.getProperty("user.dir") + "/test/actions/ProgramAction/test_program/ciao.txt";
        
        // percorso del programma che deve essere avviato da Program Action
        directory = System.getProperty("user.dir") + "/test/actions/ProgramAction/test_program/program.jar";
        pa = (new CreateProgramAction()).createProgramAction(directory, filePath);
    }
    
    /**
     * Test of getProgramPath method, of class ProgramAction.
     */
    @Test
    public void testGetProgramPath() {


        String expResult = directory;
        String result = pa.getProgramPath();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCommandLine method, of class ProgramAction.
     */
    @Test
    public void testGetCommandLine() {


        String expResult = filePath;
        String result = pa.getCommandLine();
        assertEquals(expResult, result);

    }

    /**
     * Test of execute method, of class ProgramAction.
     */
    @Test
    public void testExecute() {

        // Creo il file usato per il test
        try {
            Files.createFile(Paths.get(filePath));
        } catch (IOException ex) {
            Logger.getLogger(ProgramActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pa.execute();
        
        // Attendo che il programma attivato da ProgramAction elimini il file precedente
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgramActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertFalse(Files.exists(Paths.get(filePath)));
    }

    @Test(expected = RuntimeException.class)
    public void testAdd() {
        pa.add(pa);
    }

    @Test(expected = RuntimeException.class)
    public void testRemove() {
        pa.remove(pa);
    }

    /**
     * Test of toString method, of class ProgramAction.
     */
    @Test
    public void testToString() {

        String expResult = "Program";
        String result = pa.toString();
        assertEquals(expResult, result);

    }
    
    // Classe utilizzata per la creazione della classe ProgramAction che varia in base al sistema operativo
    
    public class CreateProgramAction {
    
    private CreatorProgramAction createProgramAction;
    
    public ProgramAction createProgramAction(String path, String parameters){
        
        String os = System.getProperty("os.name").toLowerCase();
        
        if(os.contains("win")){
            createProgramAction = new CreatorProgramActionWin(path, parameters);
        }else if(os.contains("nix") || os.contains("nux") || os.contains("mac")){
            createProgramAction = new CreatorProgramActionUnix(path, parameters);
        }
        
        return createProgramAction.create();
        
    }
}
    
}
