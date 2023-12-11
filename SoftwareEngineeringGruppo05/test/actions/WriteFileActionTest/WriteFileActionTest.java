package actions.WriteFileActionTest;

import actions.writeFileAction.WriteFileAction;
import org.junit.*;
import static org.junit.Assert.*;
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
public class WriteFileActionTest {
    
    private WriteFileAction action;
    private final String filePath = "test/actions/WriteFileActionTest/prova.txt";  //percorso del file in cui scrivere
    private final String message = "Hello World!"; //messaggio da scrivere nel file
    
    @Before
    public void setUp() {
        action = new WriteFileAction(filePath, message);
    }

    @Test
    public void testExecute() {
        Path path = Paths.get(filePath);    
        
        // scrivo qualcosa nel file di prova
        String introMessage = "Good Morning!\n";
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))){
            pw.write(introMessage);
        } catch (IOException ex) {
            Logger.getLogger(WriteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        action.execute();
        
        // leggo il contenuto del file come una lista di stringhe
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ex) {
            Logger.getLogger(WriteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // verifico se l'ultima riga del file contiene il messaggio inserito dall'utente
        assertTrue(lines.get(lines.size()-1).endsWith(message));
    }
    
    @Test
    public void testToString() {
        String expResult = "WriteFile";
        String result = action.toString();
        assertEquals(expResult, result);
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