package manager;

import controller.Controller;
import javafx.beans.property.BooleanProperty;
import javafx.scene.layout.HBox;

/**
 *
 * @author Paolo
 */
interface Manager {
    public HBox getHbox();
    public Controller getController();
    public BooleanProperty isNotCompleted();
}
