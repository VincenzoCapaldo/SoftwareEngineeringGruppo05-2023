/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rules;

import actions.Action;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import triggers.Trigger;

/**
 *
 * @author maria
 */
public class RuleTest {
    private Action action;
    private Trigger trigger;
    private Rule testRule;
    
    public RuleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        action= () -> {
        System.out.println("Action executed");
    };
    
        trigger= () -> {
        System.out.println("Trigger verified"); 
        return true;
    };
            
        testRule= new Rule("TestRule", action, trigger);
    }
    
    @AfterEach
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
        Rule instance = new Rule("newName", null, null);
        instance.setName(name);
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
        Action action = () -> {
            System.out.println("NewAction executed");
        };
        Rule instance = testRule;
        instance.setAction(action);
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
        Trigger trigger = () -> {
            System.out.println("Trigger verified"); 
            return true;
        };
        Rule instance = testRule;
        instance.setTrigger(trigger);
    }

    /**
     * Test of checkTrigger method, of class Rule.
     */
    @Test
    public void testCheckTrigger() {
        System.out.println("checkTrigger");
        Rule instance = testRule;
        instance.checkTrigger();
    }
    
}
