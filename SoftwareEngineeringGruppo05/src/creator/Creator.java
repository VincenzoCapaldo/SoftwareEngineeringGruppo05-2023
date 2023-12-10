package creator;

import controller.Controller;
import javafx.beans.property.BooleanProperty;
import javafx.scene.layout.HBox;

/**
 *
 * @author Paolo
 */
public interface Creator {
    public HBox getHbox();
    public Controller getController();
    public BooleanProperty isNotCompleted();
}
