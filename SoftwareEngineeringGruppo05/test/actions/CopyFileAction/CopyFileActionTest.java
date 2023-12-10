package actions.CopyFileAction;

import actions.copyFileAction.CopyFileAction;
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
public class CopyFileActionTest {

    private CopyFileAction action;
    private final String filePath = "test/actions/CopyFileAction/source/prova.txt"; //file da copiare
    private final String newPath = "test/actions/CopyFileAction/destination"; //cartella di destinazione

    @Before
    public void setUp() {
        action = new CopyFileAction(filePath, newPath);
    }

    @Test
    public void testExecute() {
        Path pathSource = Paths.get(filePath);    
        Path pathDestination = Paths.get(newPath + "/prova.txt");  

        // creo la cartella source, la cartella destination e il file di prova
        try {
            Files.createDirectories(Paths.get("test/actions/CopyFileAction/source"));
            Files.createDirectories(Paths.get("test/actions/CopyFileAction/destination"));
            Files.createFile(pathSource);
        } catch (IOException ex) {
            Logger.getLogger(CopyFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        action.execute();

        // verifico che ci sia ancora il file nella cartella source
        assertTrue(Files.exists(pathSource));

        // verifico che ci sia il file nella cartella destination
        assertTrue(Files.exists(pathDestination));
        
        // elimino la cartella source, la cartella destination e il file di prova (da entrambe le cartelle)
        try {
            Files.delete(pathSource);
            Files.delete(Paths.get("test/actions/CopyFileAction/source"));
            Files.delete(pathDestination);    
            Files.delete(Paths.get("test/actions/CopyFileAction/destination"));
        } catch (IOException ex) {
            Logger.getLogger(CopyFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testFileNotFoundExcute(){
        CopyFileAction cfa = new CopyFileAction("test/actions/CopyFileAction/source/fileInesistente.txt",newPath); //file inesistente
        cfa.execute();
    }
    
    @Test
    public void testToString() {
        String expResult = "CopyFile";
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