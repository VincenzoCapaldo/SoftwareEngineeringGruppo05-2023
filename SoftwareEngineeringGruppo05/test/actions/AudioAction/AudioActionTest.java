package actions.AudioAction;

import actions.audioAction.AudioAction;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Paolo
 */
public class AudioActionTest {
    
    private AudioAction action;
    private final String pathFile = "test/actions/AudioAction/notificationSound.wav"; //path audio di prova
    
    @Before
    public void setUp() {
        action = new AudioAction(pathFile);
    }
    
    @Test(expected = RuntimeException.class)
    public void testFortmatFileExcecute(){
        AudioAction aa = new AudioAction("test/actions/AudioAction/formatoSbagliato.txt"); //estensione errata
        aa.execute();
    }
    
    @Test(expected = RuntimeException.class)
    public void testFileNotFoundExcecute(){
        AudioAction aa = new AudioAction("test/actions/AudioAction/fileInesistente.wav"); //file inesistente
        aa.execute();
    }
    
    @Test
    public void testToString() {
        String expResult = "Audio";
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