package rules;

import model.rules.Rule;
import org.junit.*;
import static org.junit.Assert.*;
import model.actions.Action;
import model.actions.MessageAction;
import java.time.Duration;
import model.triggers.TimeTrigger;
import model.triggers.Trigger;

/**
 *
 * @author maria
 */
public class RuleTest {

    private Action action;
    private Trigger trigger;
    private Duration d;
    private Rule rule;

    @Before
    public void setUp() {
        //crea una regola di prova
        action = new MessageAction("Ciao");
        trigger = new TimeTrigger(11,37,true,Duration.ofMinutes(1));
        rule = new Rule("TestRule", action, trigger);
    }
 
    @Test
    public void testGetName() {
        String expResult = "TestRule";
        String result = rule.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAction() {
        Action expResult = action;
        Action result = rule.getAction();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTrigger() {
        Trigger expResult = trigger;
        Trigger result = rule.getTrigger();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetState() {
        boolean expResult = true;
        boolean result = rule.getState();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetState() {
        boolean state = false;
        rule.setState(state);
        assertEquals(state, rule.getState());
    }
    
}