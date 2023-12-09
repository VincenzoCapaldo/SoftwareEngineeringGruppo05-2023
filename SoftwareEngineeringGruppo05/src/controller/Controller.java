package controller;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author maria
 */
public interface Controller {
    public void setToggleGroup(ToggleGroup toggleGroup);
    public BooleanProperty getFlag();
}
