package actions;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import triggers.TriggerController;

/**
 *
 * @author maria
 */
public interface ActionController {
    public CheckBox getCB();
    public BooleanProperty getFlag();
}
