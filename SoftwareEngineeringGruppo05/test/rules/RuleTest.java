package rules;

import org.junit.*;
import static org.junit.Assert.*;
import actions.Action;
import actions.MessageAction.MessageAction;
import java.time.Duration;
import triggers.TimeTrigger.TimeTrigger;
import triggers.Trigger;

/**
 *
 * @author Maria
 */
public class RuleTest {

    private Action action;
    private Trigger trigger;
    private Duration d;
    private Rule rule;

    @Before
    public void setUp() {
        action = new MessageAction("Ciao");
        trigger = new TimeTrigger(11,37);
        d = Duration.ofMinutes(1);
        rule = new Rule("TestRule", action, trigger, false, true, d);
    }
 
    @Test
    public void testGetName() {
        String expResult = "TestRule";
        String result = rule.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName() {
        String name = "newName";
        rule.setName(name);
        assertEquals(name, rule.getName());
    }

    @Test
    public void testGetAction() {
        Action expResult = action;
        Action result = rule.getAction();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAction() {
        action = new MessageAction("Ciao2");
        rule.setAction(action);
        assertEquals(action, rule.getAction());
    }

    @Test
    public void testGetTrigger() {
        Trigger expResult = trigger;
        Trigger result = rule.getTrigger();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetTrigger() {
        trigger = new TimeTrigger(23,22);
        rule.setTrigger(trigger);
        assertEquals(trigger, rule.getTrigger());
    }
    
    @Test
    public void testGetState() {
        boolean expResult = false;
        boolean result = rule.getState();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetState() {
        boolean state = true;
        rule.setState(state);
        assertEquals(state, rule.getState());
    }
    
    @Test
    public void testGetRepeat() {
        boolean expResult = true;
        boolean result = rule.getRepeate();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetRepeat() {
        boolean repetition = false;
        rule.setRepeate(repetition);
        assertEquals(repetition, rule.getState());
    }
    
    @Test
    public void testGetSleeping() {
        Duration expResult = d;
        Duration result = rule.getSleeping();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetSleeping() {
        Duration d1 = Duration.ofMinutes(2);
        rule.setSleeping(d1);
        assertEquals(d1, rule.getSleeping());
    }
    
}