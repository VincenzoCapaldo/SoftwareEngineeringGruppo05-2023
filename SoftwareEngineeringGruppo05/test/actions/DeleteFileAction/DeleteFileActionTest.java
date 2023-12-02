package actions.DeleteFileAction;

import org.junit.*;
import static org.junit.Assert.*;
import actions.Action;
import java.io.BufferedWriter;
import java.io.File;
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

    private static final String TEST_FILE_PATH = getPath("testfile.txt");
    private DeleteFileAction action;

    public DeleteFileActionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
         // Crea il file di test e scrivi "buongiorno!" in esso
        try {
            Path filePath = Paths.get(TEST_FILE_PATH);
            Files.createFile(filePath);

            // Scrivi "buongiorno!" nel file
            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                writer.write("buongiorno!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        action = new DeleteFileAction(TEST_FILE_PATH);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class DeleteFileAction.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");

        // Crea l'istanza di CopyFileAction
        DeleteFileAction instance = action;

        // Esegui il test
        instance.execute();

        // Verifica che il file non esiste
        assertTrue(Files.notExists(Paths.get(TEST_FILE_PATH)));    
    }

    @Test(expected = RuntimeException.class)
    public void testAdd() {
        action.add(action);
    }

    @Test(expected = RuntimeException.class)
    public void testRemove() {
        action.remove(action);
    }

    private static String getPath(String relativePath) {
        try {
            // directory di lavoro del progetto
            String projectDir = System.getProperty("user.dir");

            //percorso completo utilizzando il separatore di percorso standard
            return projectDir + File.separator + "test" + File.separator + "actions" + File.separator + "DeleteFileAction" + File.separator + relativePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}