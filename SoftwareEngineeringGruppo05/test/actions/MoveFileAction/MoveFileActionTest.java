package actions.MoveFileAction;

import actions.moveFileAction.MoveFileAction;
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
 * @author Paolo
 */
public class MoveFileActionTest {
    
    private MoveFileAction action;
    private final String filePath = "test/actions/MoveFileAction/source/prova.txt"; //file da spostare
    private final String newPath = "test/actions/MoveFileAction/destination"; //cartella di destinazione
    
    @Before
    public void setUp() {
        action = new MoveFileAction(filePath, newPath);
    }

    @Test
    public void testExecute() {
        Path pathSource = Paths.get(filePath);    
        Path pathDestination = Paths.get(newPath + "/prova.txt");    
        
        // creo la cartella source, la cartella destination e il file di prova
        try {
            Files.createDirectories(Paths.get("test/actions/MoveFileAction/source"));
            Files.createDirectories(Paths.get("test/actions/MoveFileAction/destination"));
            Files.createFile(pathSource);
        } catch (IOException ex) {
            Logger.getLogger(MoveFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        action.execute();
        
        // verifico che non ci sia più il file nella cartella source
        assertFalse(Files.exists(pathSource));
        
        // verifico che ci sia il file nella cartella destination
        assertTrue(Files.exists(pathDestination));
        
        // elimino la cartella source, la cartella destination e il file di prova (dalla cartella destination)
        try {
            Files.delete(pathDestination);
            Files.delete(Paths.get("test/actions/MoveFileAction/source"));
            Files.delete(Paths.get("test/actions/MoveFileAction/destination"));
        } catch (IOException ex) {
            Logger.getLogger(MoveFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected = RuntimeException.class)
    public void testFileNotFoundExcute(){
        MoveFileAction mfa = new MoveFileAction("test/actions/MoveFileAction/source/fileInesistente.txt",newPath); //file inesistente
        mfa.execute();
    }
    
    @Test
    public void testToString() {
        String expResult = "MoveFile";
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