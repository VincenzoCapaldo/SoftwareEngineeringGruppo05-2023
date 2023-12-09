package actions.MoveFileAction;

import model.actions.MoveFileAction;
import org.junit.*;
import static org.junit.Assert.*;
import model.actions.Action;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paolo
 */
public class MoveFileActionTest {
    
    private Action action;
    private final String filePath = "test/actions/MoveFileAction/source/prova.txt";
    private final String newPath = "test/actions/MoveFileAction/destination";
    
    @Before
    public void setUp() {
        action = new MoveFileAction(filePath, newPath);
    }

    @Test
    public void testExecute() {
        Path pathSource = Paths.get(filePath);    
        Path pathDestination = Paths.get(newPath + "/prova.txt");    
        
        // creo un file di prova nella cartella source
        try {
            Files.createFile(pathSource);
        } catch (IOException ex) {
            Logger.getLogger(MoveFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        action.execute();
        
        // verifico che non ci sia più il file nella cartella source
        assertFalse(Files.exists(pathSource));
        
        // verifico che ci sia il file nella cartella destination
        assertTrue(Files.exists(pathDestination));
        
        // elimino il file di prova nella cartella destination
        try {
            Files.delete(pathDestination);
        } catch (IOException ex) {
            Logger.getLogger(MoveFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
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