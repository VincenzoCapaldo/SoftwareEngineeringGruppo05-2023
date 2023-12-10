package rules;

import model.rules.RuleManager;
import model.rules.Rule;
import org.junit.*;
import static org.junit.Assert.*;
import model.actions.Action;
import model.actions.MessageAction;
import java.time.Duration;
import model.triggers.Trigger;
import java.util.Set;
import model.triggers.TimeTrigger;

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
        //crea una regola di prova
        action = new MessageAction("Ciao");
        trigger = new TimeTrigger(11,37,true,Duration.ofMinutes(1));
        rule = new Rule("TestRule", action, trigger);
        
        //istanzia il RuleManager
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
        //aggiunge la regola e poi controlla che il set di regole non è vuoto
        ruleManager.addRule(rule);
        Set<Rule> rules = ruleManager.getRules();
        assertFalse(rules.isEmpty());
        ruleManager.deleteRule(rule); //pulizia
    }    
    
    @Test
    public void testAddRule() {
        //aggiunge la regola e poi controlla che è presente nel set di regole
        ruleManager.addRule(rule);
        Set<Rule> rules = ruleManager.getRules();
        assertTrue(rules.contains(rule));
        ruleManager.deleteRule(rule); //pulizia
    }
    
    @Test
    public void testDeleteRule() {
        //aggiunge ed elimina la regola, poi controlla che non è contenuta nel set di regole
        ruleManager.addRule(rule);
        ruleManager.deleteRule(rule);
        Set<Rule> rules = ruleManager.getRules();
        assertFalse(rules.contains(rule));
    }
    
    public void testDeactivateRule() {
        //disattiva la regola e controlla se lo stato è diventato false
        ruleManager.deactivateRule(rule);
        boolean expResult = false;
        boolean result = rule.getState();
        assertEquals(expResult, result);
    }

    public void testReactivateRule() {
        //disattiva e riattiva la regola, poi controlla se lo stato è tornato true
        ruleManager.deactivateRule(rule);
        ruleManager.reactivateRule(rule);
        boolean expResult = true;
        boolean result = rule.getState();
        assertEquals(expResult, result); 
    }
    
}