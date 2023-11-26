/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package actions.AudioAction;

import actions.Action;
import actions.MessageAction.MessageAction;
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
        action = new MessageAction("Ciao");
    }
    
    @Test(expected = RuntimeException.class)
    public void testFortmatFileAudioAction(){
        AudioAction aa = new AudioAction("prova.txt");
    }
    
    @Test(expected = RuntimeException.class)
    public void testFileNotFoundAction(){
        AudioAction aa = new AudioAction("prova2.txt");
    }
    
    @Test(expected = RuntimeException.class)
    public void testAdd() {
        AudioAction aa = new AudioAction("notifactionSound.wav");
        aa.add(action);
    }

    @Test(expected = RuntimeException.class)
    public void testRemove() {
        AudioAction aa = new AudioAction("notifactionSound.wav");
        aa.remove(action);
    }
    
}
