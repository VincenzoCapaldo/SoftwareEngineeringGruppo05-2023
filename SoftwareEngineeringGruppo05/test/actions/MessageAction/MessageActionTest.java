package actions.MessageAction;

import actions.messageAction.MessageAction;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author maria
 */
public class MessageActionTest {
   
    private MessageAction action;
    private final String message = "prova"; //messaggio di prova
            
            
    @Before
    public void setUp() {
        action = new MessageAction("prova");
    }
    
    @Test
    public void testGetName() {
        String expResult = "prova";
        String result = action.getMessage();
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
    
    @Test
    public void testToString() {

        String expResult = "Message";
        String result = action.toString();
        assertEquals(expResult, result);

    }
    
}
