package triggers;

import java.time.LocalTime;

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
        
}
