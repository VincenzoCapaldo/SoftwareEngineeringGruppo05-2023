package actions.DeleteFileAction;

import actions.deleteFileAction.DeleteFileAction;
import org.junit.*;
import static org.junit.Assert.*;
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

    private DeleteFileAction action;
    private final String directoryPath = "test/actions/DeleteFileAction"; //directory del file da eliminare
    private final String nameFile = "prova.txt"; //nome del file da eliminare
    
    @Before
    public void setUp() {
        action = new DeleteFileAction(directoryPath, nameFile);
    }

    @Test
    public void testExecute() {
        Path path = Paths.get(directoryPath + "/" + nameFile);    
        
        // creo il file di prova
        try {
            Files.createFile(path);
        } catch (IOException ex) {
            Logger.getLogger(DeleteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        action.execute();

        // verifico che non ci sia più il file nella cartella
        assertFalse(Files.exists(path));  
    }
    
    @Test(expected = RuntimeException.class)
    public void testFileNotFoundExcute(){
        DeleteFileAction dfa = new DeleteFileAction(directoryPath,"fileInesistente.txt"); //file inesistente
        dfa.execute();
    }
    
    @Test(expected = RuntimeException.class)
    public void testAdd() {
        action.add(action);
    }

    @Test(expected = RuntimeException.class)
    public void testRemove() {
        action.remove(action);
    }
    
    @Test
    public void testToString() {

        String expResult = "DeleteFile";
        String result = action.toString();
        assertEquals(expResult, result);

    }

}