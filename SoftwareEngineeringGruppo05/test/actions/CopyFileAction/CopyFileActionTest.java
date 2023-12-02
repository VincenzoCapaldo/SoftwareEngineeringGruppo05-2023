/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package actions.CopyFileAction;

import actions.Action;
import actions.CopyFileAction.CopyFileAction;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maria
 */
public class CopyFileActionTest {

    private static final String TEST_FILE_PATH = getPath("testFile.txt");
    private static final String DESTINATION_PATH = getPath("newPath");

    private CopyFileAction action;

    public CopyFileActionTest() {
    }

    @BeforeClass
    public static void setUpClass() {     
        try {
             // Crea il file di test e scrivi "buongiorno!" in esso
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
        try {
           // Elimina il file di test
            Files.deleteIfExists(Paths.get(TEST_FILE_PATH));

            // Elimina il file nella nuova directory
            Files.deleteIfExists(Paths.get(DESTINATION_PATH, "testfile.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        action = new CopyFileAction(TEST_FILE_PATH, DESTINATION_PATH);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testExecute() {
        System.out.println("execute");

        // Crea l'istanza di CopyFileAction
        CopyFileAction instance = action;

        // Esegui il test
        instance.execute();

        // Verifica che il file di destinazione esista
        assertTrue(Files.exists(Paths.get(DESTINATION_PATH)));
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
            return projectDir + File.separator + "test" + File.separator + "actions" + File.separator + "CopyFileAction" + File.separator + relativePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}