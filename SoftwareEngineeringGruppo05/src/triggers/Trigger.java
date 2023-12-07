package triggers;

import java.io.Serializable;

/**
 *
 * @author Luca
 */
public interface Trigger extends Serializable {
    public void checkTrigger();
    public boolean isVerified();
    public boolean isRepeated();
}