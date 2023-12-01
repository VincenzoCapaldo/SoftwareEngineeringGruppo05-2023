package actions.WriterAction;

import actions.Action;
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
 * @author Paolo
 */
public class WriterActionTest {
    
    private Action action;
    private final String filePath = "test/actions/WriterAction/prova.txt";
    private final String message = "Hello World!\n";
    
    @Before
    public void setUp() {
        action = new WriterAction(filePath, message);
    }

    @Test
    public void testExecute() {
        action.execute(); //inserisco il messaggio alla fine del file

        String fileContent = null;
        try {
            // inserisco nella variabile fileContent il contenuto del file come stringa
            fileContent = new String(Files.readAllBytes(Paths.get(filePath))); 
        } catch (IOException ex) {
            Logger.getLogger(WriterActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // verifico se il file termina con il messaggio inserito dall'utente
        assertTrue(fileContent.endsWith(message));
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
