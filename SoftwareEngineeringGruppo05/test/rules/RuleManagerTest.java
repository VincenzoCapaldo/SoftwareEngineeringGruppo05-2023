package rules;

import org.junit.*;
import static org.junit.Assert.*;
import actions.Action;
import actions.MessageAction.MessageAction;
import triggers.Trigger;
import java.util.List;
import triggers.TimeTrigger.TimeTrigger;

/**
 *
 * @author Paolo
 */
public class RuleManagerTest {
    
    private Action action;
    private Trigger trigger;
    private Rule rule;
    private RuleManager ruleManager;

    @Before
    public void setUp() {       
        action = new MessageAction("Ciao");
        trigger = new TimeTrigger(11,37);
        rule = new Rule("TestRule", action, trigger);
        ruleManager = RuleManager.getInstance(); 
    }

    @Test
    public void testGetInstance() {
        RuleManager instance1 = RuleManager.getInstance();
        RuleManager instance2 = RuleManager.getInstance();
        assertSame(instance1, instance2);
        /* assertSame verifica che due oggetti siano la stessa istanza,
        cioè che puntino allo stesso oggetto in memoria. */
    }

    @Test
    public void testGetRules() {
        ruleManager.addRule(rule);
        List<Rule> rules = ruleManager.getRules();
        assertFalse(rules.isEmpty());
    }
    
    @Test
    public void testAddRule() {
        ruleManager.addRule(rule);
        List<Rule> rules = ruleManager.getRules();
        assertTrue(rules.contains(rule));
    }
    
}