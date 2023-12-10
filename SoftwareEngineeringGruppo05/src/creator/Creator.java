package creator;

import javafx.beans.property.BooleanProperty;
import javafx.scene.layout.HBox;

/**
 *
 * @author Paolo
 */
public interface Creator {
    public HBox getHbox();
    public BooleanProperty isNotCompleted();
}
