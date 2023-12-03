package actions.ProgramAction;

import org.junit.*;
import static org.junit.Assert.*;
import actions.Action;

/**
 *
 * @author Paolo
 */
public class ProgramActionTest {
    
    private Action action;
    private String programPath;
    private String commandLine;

    @Before
    public void setUp() {
        action = new ProgramAction(programPath, commandLine);
    }
    
    @Test
    public void testExecute(){
        
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
