package actions.WriterAction;

import actions.Action;
import actions.AudioAction.AudioAction;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paolo
 */
public class WriterActionTest {
    
    Action action;
    String filePath = "test/actions/WriterAction/prova.txt";
    String message = "Hello, World!\nThis is a new line.";
    
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
    
    @Test(expected = IOException.class)
    public void testFortmatFileExecute(){
        WriterAction wa = new WriterAction("test/actions/WiterAction/notificationSound.wav", message);
        action.execute();
    }
    
    @Test(expected = IOException.class)
    public void testFileNotFoundExecute(){
        WriterAction wa = new WriterAction("test/actions/WriterAction/fileInesistente.txt", message);
        action.execute();
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
