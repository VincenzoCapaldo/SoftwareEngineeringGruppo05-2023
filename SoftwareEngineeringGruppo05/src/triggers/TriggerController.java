package triggers;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author maria
 */
public interface TriggerController {
    public void setToggleGroup(ToggleGroup toggleGroup);
    public BooleanProperty getFlag();
}
