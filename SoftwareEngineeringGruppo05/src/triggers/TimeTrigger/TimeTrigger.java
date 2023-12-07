package triggers.TimeTrigger;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import triggers.Trigger;

/**
 *
 * @author Luca
 */
public class TimeTrigger extends Observable implements Trigger{
    
    private LocalTime time;
    private boolean repetition;
    private Duration sleeping;
    private boolean verified;
    
    private static final int MILLISECONDS_DAY = 86400000; //millisecondi in un giorno    
    
    public TimeTrigger(int hour, int minute, boolean repetition, Duration sleeping){
        this.time = LocalTime.of(hour, minute);
        this.repetition = repetition;
        this.sleeping = sleeping;
    }
    
    @Override
    public boolean isVerified() {
        return verified;
    }

    public boolean isRepeated() {
        return repetition;
    }

    @Override
    public void checkTrigger() {
        
        verified = false;
        
        if(LocalTime.now().getHour()==this.time.getHour() && LocalTime.now().getMinute()==this.time.getMinute()){
            verified = true;
            setChanged();
            notifyObservers();
        }else{
            long attesa;
            if(LocalTime.now().isBefore(time)){
                attesa = Duration.between(LocalTime.now(), time).toMillis();
            }else{
                attesa = MILLISECONDS_DAY - Duration.between(time, LocalTime.now()).toMillis();
            }
            try {
                Thread.sleep(attesa);
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        verified = false;
        
        while(repetition && sleeping.toMillis()!=0){
            try {
                Thread.sleep(sleeping.toMillis());
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}