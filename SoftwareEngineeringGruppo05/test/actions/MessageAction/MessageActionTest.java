package actions.MessageAction;

import model.actions.MessageAction;
import model.actions.Action;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Paolo
 */
public class MessageActionTest {
    Action action;
    
    @Before
    public void setUp() {
        action = new MessageAction("prova");
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
