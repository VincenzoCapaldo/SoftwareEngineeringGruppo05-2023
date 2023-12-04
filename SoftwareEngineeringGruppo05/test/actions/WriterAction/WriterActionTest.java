package actions.WriterAction;

import org.junit.*;
import static org.junit.Assert.*;
import actions.Action;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paolo
 */
public class WriterActionTest {
    
    private Action action;
    private final String filePath = "test/actions/WriterAction/prova.txt";
    private final String message = "Hello World!";
    
    @Before
    public void setUp() {
        action = new WriterAction(filePath, message);
    }

    @Test
    public void testExecute() {
        Path path = Paths.get(filePath);    
        
        // creo un file di prova
        try {
            Files.createFile(path);
        } catch (IOException ex) {
            Logger.getLogger(WriterActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // scrivo qualcosa nel file di prova
        String introMessage = "Good Morning!\n";
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))){
            pw.write(introMessage);
        } catch (IOException ex) {
            Logger.getLogger(WriterActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        action.execute(); // eseguo WriterAction
        
        // leggo il contenuto del file come una lista di stringhe
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ex) {
            Logger.getLogger(WriterActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // verifico se l'ultima riga del file contiene il messaggio inserito dall'utente
        assertTrue(lines.get(lines.size()-1).endsWith(message));
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
