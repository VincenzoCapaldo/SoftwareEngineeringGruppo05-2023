package model.triggers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DateTrigger extends Observable implements Trigger{
    
    private LocalDate data;
    private boolean verified;
    
    public DateTrigger(LocalDate data){
        this. data = data;
    }
    
    @Override
    public boolean isVerified() {
        return this.verified;
    }

    @Override
    public boolean isRepeated() {
        return false;
    }
    
    @Override
    public void checkTrigger() {
        
        verified = false;
        setChanged();
               
        if(LocalDate.now().equals(data)){
            verified = true;
            setChanged();
            notifyObservers();
        }else{
            long wait;
            wait = Duration.between(LocalDate.now().atTime(LocalTime.now()), data.atTime(LocalTime.MIDNIGHT)).toMillis();
            try {
                Thread.sleep(wait);
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(DateTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}