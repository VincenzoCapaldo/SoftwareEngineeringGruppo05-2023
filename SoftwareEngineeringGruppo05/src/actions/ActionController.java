package actions;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author maria
 */
public interface ActionController {
    
    public void setToggleGroup(ToggleGroup toggleGroup);
    public BooleanProperty getFlag();

}
