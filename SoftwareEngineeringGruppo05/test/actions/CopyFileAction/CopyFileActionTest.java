package actions.CopyFileAction;

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
public class CopyFileActionTest {
    
    private Action action;
    private final String filePath = "test/actions/CopyFileAction/source/prova.txt";
    private final String newPath = "test/actions/CopyFileAction/destination";
    
    @Before
    public void setUp() {
        action = new CopyFileAction(filePath, newPath);
    }
    
    @After
    public void tearDown() {
        // elimino il file di prova nella cartella source
        Path path1 = Paths.get(filePath);    
        try {
            Files.delete(path1);
        } catch (IOException ex) {
            Logger.getLogger(CopyFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // elimino il file di prova nella cartella destination
        Path path2 = Paths.get(newPath + "/prova.txt");    
        try {
            Files.delete(path2);
        } catch (IOException ex) {
            Logger.getLogger(CopyFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testExecute() {
        // creo un file di prova nella cartella source
        Path path = Paths.get(filePath);    
        try {
            Files.createFile(path);
        } catch (IOException ex) {
            Logger.getLogger(CopyFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        action.execute();
        
        // verifico che ci sia ancora il file nella cartella source
        assertTrue(Files.exists(Paths.get(filePath)));
        
        // verifico che ci sia il file nella cartella destination
        assertTrue(Files.exists(Paths.get(newPath + "/prova.txt")));    
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