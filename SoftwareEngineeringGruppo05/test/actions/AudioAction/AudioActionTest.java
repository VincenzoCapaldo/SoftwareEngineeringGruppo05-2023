package actions.AudioAction;

import model.actions.AudioAction;
import org.junit.*;
import model.actions.Action;

/**
 *
 * @author Paolo
 */
public class AudioActionTest {
    
    private Action action;
    private final String pathFile = "test/actions/AudioAction/notificationSound.wav"; //path audio di prova
    
    @Before
    public void setUp() {
        action = new AudioAction(pathFile);
    }
    
    @Test(expected = RuntimeException.class)
    public void testFortmatFileAudioAction(){
        AudioAction aa = new AudioAction("test/actions/AudioAction/formatoSbagliato.txt"); //estensione errata
    }
    
    @Test(expected = RuntimeException.class)
    public void testFileNotFoundAudioAction(){
        AudioAction aa = new AudioAction("test/actions/AudioAction/fileInesistente.wav"); //file inesistente
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