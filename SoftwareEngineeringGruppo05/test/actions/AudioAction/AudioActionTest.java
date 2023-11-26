package actions.AudioAction;

import actions.Action;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author enzo0
 */
public class AudioActionTest {
    Action action;
    
    @Before
    public void setUp() {
        action = new AudioAction("test/actions/AudioAction/notificationSound.wav");
    }
    
    @Test(expected = RuntimeException.class)
    public void testFortmatFileAudioAction(){
        AudioAction aa = new AudioAction("test/actions/AudioAction/prova.txt");
    }
    
    @Test(expected = RuntimeException.class)
    public void testFileNotFoundAction(){
        AudioAction aa = new AudioAction("test/actions/AudioAction/prova2.txt");
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
