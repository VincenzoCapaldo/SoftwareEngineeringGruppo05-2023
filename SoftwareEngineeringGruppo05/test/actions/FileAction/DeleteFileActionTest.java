/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package actions.FileAction;

import actions.Action;
import java.io.BufferedWriter;
import java.io.IOException;
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
public class DeleteFileActionTest {
    
    private static final String TEST_FILE_PATH = getPath("oldPath/testfile.txt");
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
            // Restituisci un percorso assoluto noto per il file di test
            return "C:\\Users\\maria\\Documents\\NetBeansProjects\\SoftwareEngineeringGruppo05-2023\\" + relativePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
