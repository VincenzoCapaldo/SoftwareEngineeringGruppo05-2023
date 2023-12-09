package model.actions;

import java.io.Serializable;

/**
 *
 * @author Luca
 */
public interface Action extends Serializable{
    public void execute(); 
    public void add(Action a);
    public void remove(Action a);
}
