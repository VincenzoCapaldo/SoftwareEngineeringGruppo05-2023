package controller.actions;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import controller.triggers.TriggerController;

/**
 *
 * @author maria
 */
public interface ActionController {
    public CheckBox getCB();
    public BooleanProperty getFlag();
}
