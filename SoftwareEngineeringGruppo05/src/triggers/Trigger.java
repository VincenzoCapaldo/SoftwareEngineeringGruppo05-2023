package triggers;

import java.io.Serializable;

/**
 *
 * @author Luca
 */
public interface Trigger extends Serializable {
    public boolean isVerified();
    public boolean isRepeated();
    public void checkTrigger(); 
}