package actions;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.HBox;

/**
 *
 * @author Paolo
 */
public abstract class ActionCreator {
    
    private FXMLLoader fxmlLoader;
    private ActionController controller;
    private HBox hbox;
    
    public ActionCreator(String path) {
        // crea l'FXML e gli associa il corrispettivo controller
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        try {
            hbox = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ActionCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller = fxmlLoader.getController();
    }
    
    public HBox getHbox() {
        return hbox;
    }

    public ActionController getController() {
        return controller;
    }
    
    public abstract Action createAction();
    
}