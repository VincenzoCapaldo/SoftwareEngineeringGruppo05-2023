package triggers.TimeTrigger;

import java.time.Duration;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import triggers.Trigger;

/**
 *
 * @author Luca
 */
public class TimeTrigger implements Trigger{
    private LocalTime time;
    
    public TimeTrigger(int hour, int minute){
        this.time = LocalTime.of(hour, minute);
    }

    @Override
    public boolean verify() {
        return (LocalTime.now().getHour()==this.time.getHour() && 
                LocalTime.now().getMinute()==this.time.getMinute());
    }

    @Override
    public boolean checkTrigger() {
        
        if(LocalTime.now().isBefore(time)){
            try {
                Thread.sleep(Duration.between(LocalTime.now(), time).toMillis());
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(!this.verify()){
            try {
                Thread.sleep(86400000 - Duration.between(time, LocalTime.now()).toMillis());
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return this.verify();
    }
        
}
