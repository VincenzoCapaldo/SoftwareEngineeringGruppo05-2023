package rules;

import org.junit.*;
import static org.junit.Assert.*;
import actions.Action;
import actions.MessageAction.MessageAction;
import triggers.TimeTrigger;
import triggers.Trigger;

public class RuleTest {

    private Action action;
    private Trigger trigger;
    private Rule rule;

    @Before
    public void setUp() {
        action = new MessageAction("Ciao");
        trigger = new TimeTrigger(11,37);
        rule = new Rule("TestRule", action, trigger);
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
    
}
