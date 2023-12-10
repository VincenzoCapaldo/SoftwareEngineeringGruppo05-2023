package model.actions;

/**
 *
 * @author Paolo
 */
import java.util.ArrayList;
import java.util.List;

public class CompositeAction implements Action {

    private List<Action> actions; //lista di riferimenti a oggetti dell'interfaccia Action

    public CompositeAction() {
        this.actions = new ArrayList<>();
    }

    @Override
    public void execute() {
        for (Action action : actions) {
            action.execute(); //itero la lista di azioni per eseguirle
        }
    }

    @Override
    public String toString(){
        String allActions = "";
        for (Action action : actions) {
            allActions += action.toString() + " "; //itero la lista di azioni per eseguirle
        }
        return allActions;
    }
    
    @Override
    public void add(Action action) {
        actions.add(action); //aggiunge azione alla lista
    }

    @Override
    public void remove(Action action) {
        actions.remove(action); //rimuove azione dalla lista
    }
    
}