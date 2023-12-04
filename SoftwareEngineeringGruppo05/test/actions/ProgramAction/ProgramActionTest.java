package actions.ProgramAction;

import org.junit.*;
import static org.junit.Assert.*;
import actions.Action;
import actions.DeleteFileAction.DeleteFileActionTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paolo
 */
public class ProgramActionTest {
    
    /* Il programma di prova da lanciare elimina un file e
    bisogna passargli il path del file da riga di comando */
        
    private Action action;
    private final String programPath = "test/actions/ProgramAction/DeleteFile.jar";
    private final String commandLine = "test/actions/ProgramAction/prova.txt";

    @Before
    public void setUp() {
        action = new ProgramAction(programPath, commandLine);
    }
    
    @Test
    public void testExecute() {
 
        // creo il file di prova
        try {
            Files.createFile(Paths.get(commandLine));
        } catch (IOException ex) {
            Logger.getLogger(DeleteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        action.execute(); // lancio il programma esterno
        
        // verifico che non ci sia più il file nella cartella
        assertFalse(Files.exists(Paths.get(commandLine)));  
        
        // elimino il file di prova
        try {
            Files.delete(Paths.get(commandLine));
        } catch (IOException ex) {
            Logger.getLogger(DeleteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test(expected = RuntimeException.class)
    public void testAdd() {
        action.add(action);
    }

    @Test(expected = RuntimeException.class)
    public void testRemove() {
        action.remove(action);
    }
    
}