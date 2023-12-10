package actions;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author maria
 */
public interface ActionController {
    public CheckBox getCB();
    public BooleanProperty getFlag();
}
