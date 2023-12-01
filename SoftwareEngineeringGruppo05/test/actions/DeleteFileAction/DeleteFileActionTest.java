package actions.DeleteFileAction;

import org.junit.*;
import static org.junit.Assert.*;
import actions.Action;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class DeleteFileActionTest {
    
    private Action action;
    private final String filePath = "test/actions/DeleteFileAction/prova.txt";
    
    @Before
    public void setUp() {
        action = new DeleteFileAction(filePath);
    }
    
    @After
    public void tearDown() {
        // elimino il file di prova
        Path path = Paths.get(filePath);    
        try {
            Files.delete(path);
        } catch (IOException ex) {
            Logger.getLogger(DeleteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testExecute() {
        // creo un file di prova nella cartella
        Path path = Paths.get(filePath);    
        try {
            Files.createFile(path);
        } catch (IOException ex) {
            Logger.getLogger(DeleteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        action.execute();
        
        // verifico che non ci sia più il file nella cartella
        assertFalse(Files.exists(Paths.get(filePath)));  
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