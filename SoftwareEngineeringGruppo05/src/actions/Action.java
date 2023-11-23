package actions;

/**
 *
 * @author Luca
 */
public interface Action {
    public void execute(); 
    public void add(Action a);
    public void remove(Action a);
}
