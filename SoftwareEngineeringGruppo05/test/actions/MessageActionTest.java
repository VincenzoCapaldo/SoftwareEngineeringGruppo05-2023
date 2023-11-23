/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package actions;

import actions.MessageActionPackage.MessageAction;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author maria
 */
public class MessageActionTest {
    
    private MessageAction message;
    private Action action;
    
    public MessageActionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        message= new MessageAction("ciao");
        
        action = () -> {
            System.out.println("Action executed");
        };
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class MessageAction.
     * expected = UnsupportedOperationException.class
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        System.out.println("add");
        Action a = action;
        MessageAction instance = new MessageAction("mondo");
        instance.add(a);
    }

    /**
     * Test of remove method, of class MessageAction.
     * expected = UnsupportedOperationException.class
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        System.out.println("remove");
        Action a = action;
        MessageAction instance = message;
        instance.remove(a);
    }
    
}
