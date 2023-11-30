package triggers;

import java.io.Serializable;

/**
 *
 * @author Luca
 */
public interface Trigger extends Serializable{
    public boolean verify();
    public boolean checkTrigger();
}
