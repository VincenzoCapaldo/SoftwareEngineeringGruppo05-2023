package manager;

import actions.Action;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import controller.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.HBox;

/**
 *
 * @author Paolo
 */
public abstract class ActionManager implements Manager{
    
    private FXMLLoader fxmlLoader;
    private Controller controller;
    private HBox hbox;
    
    public ActionManager(String path) {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        try {
            hbox = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ActionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller = fxmlLoader.getController();
    }
    
    @Override
    public HBox getHbox() {
        return hbox;
    }

    @Override
    public Controller getController() {
        return controller;
    }
    
    @Override
    public BooleanProperty isNotCompleted(){
        return controller.getFlag();
    }
    
    public abstract Action createAction();
    
}