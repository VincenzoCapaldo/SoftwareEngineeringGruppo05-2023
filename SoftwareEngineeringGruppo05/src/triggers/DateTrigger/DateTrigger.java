package triggers.DateTrigger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import triggers.Trigger;

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
        return verified;
    }

    @Override
    public void checkTrigger() {
        
        verified = false;
        
        if(LocalDate.now().equals(data)){
            verified = true;
            setChanged();
            notifyObservers();
        }else{
            long attesa;
            attesa = Duration.between(LocalDate.now().atTime(LocalTime.now()), data.atTime(0,0)).toMillis();
            try {
                Thread.sleep(attesa);
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(DateTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}