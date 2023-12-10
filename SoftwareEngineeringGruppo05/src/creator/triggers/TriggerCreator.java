package creator.triggers;

import creator.Creator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import model.rules.Rule;
import model.triggers.Trigger;
import controller.triggers.TriggerController;

/**
 *
 * @author Paolo
 */
public abstract class TriggerCreator implements Creator{
    
    private FXMLLoader fxmlLoader;
    private TriggerController controller;
    private HBox hbox;
    
    public TriggerCreator(String path) {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        try {
            hbox = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(TriggerCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller = fxmlLoader.getController();
    }
    
    @Override
    public HBox getHbox() {
        return hbox;
    }

    public TriggerController getController() {
        return controller;
    }
    
    @Override
    public BooleanProperty isNotCompleted(){
        return controller.getFlag();
    }
    
    public abstract Trigger createTrigger();
    
    public abstract void addObserver(Rule rule);

}
