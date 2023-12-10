package triggers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import rule.Rule;

/**
 *
 * @author Paolo
 */
public abstract class TriggerCreator {
    
    private FXMLLoader fxmlLoader;
    private TriggerController controller;
    private HBox hbox;
    
    public TriggerCreator(String path) {
        // crea l'FXML e gli associa il corrispettivo controller
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        try {
            hbox = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(TriggerCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller = fxmlLoader.getController();
    }
    
    public HBox getHbox() {
        return hbox;
    }

    public TriggerController getController() {
        return controller;
    }
    
    public abstract Trigger createTrigger();
    
    public abstract void addObserver(Rule rule); //la regola osserva il trigger

}
