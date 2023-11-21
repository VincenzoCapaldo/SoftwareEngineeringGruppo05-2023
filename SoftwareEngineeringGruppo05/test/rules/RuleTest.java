package rules;

import actions.Action;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import triggers.Trigger;

public class RuleTest {

    private Action action;
    private Trigger trigger;
    private Rule testRule;

    public RuleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        action = () -> {
            System.out.println("Action executed");
        };

        trigger = () -> {
            System.out.println("Trigger verified");
            return true;
        };

        testRule = new Rule("TestRule", action, trigger);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Rule.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Rule instance = testRule;
        String expResult = "TestRule";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Rule.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "newName";
        Rule instance = testRule;
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getAction method, of class Rule.
     */
    @Test
    public void testGetAction() {
        System.out.println("getAction");
        Rule instance = testRule;
        Action expResult = action;
        Action result = instance.getAction();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAction method, of class Rule.
     */
    @Test
    public void testSetAction() {
        System.out.println("setAction");
        Action newAction = () -> {
            System.out.println("NewAction executed");
        };
        Rule instance = testRule;
        instance.setAction(newAction);
        assertEquals(newAction, instance.getAction());
    }

    /**
     * Test of getTrigger method, of class Rule.
     */
    @Test
    public void testGetTrigger() {
        System.out.println("getTrigger");
        Rule instance = testRule;
        Trigger expResult = trigger;
        Trigger result = instance.getTrigger();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTrigger method, of class Rule.
     */
    @Test
    public void testSetTrigger() {
        System.out.println("setTrigger");
        Trigger newTrigger = () -> {
            System.out.println("NewTrigger verified");
            return true;
        };
        Rule instance = testRule;
        instance.setTrigger(newTrigger);
        assertEquals(newTrigger, instance.getTrigger());
    }
    
}
