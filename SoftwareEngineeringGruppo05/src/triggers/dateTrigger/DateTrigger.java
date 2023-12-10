package triggers.dateTrigger;

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
    
    private LocalDate data; //data in cui scatta il trigger
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
               
        if(LocalDate.now().equals(data)){ //se la data corrisponde alla data attuale
            verified = true;
            setChanged();
            notifyObservers();
        }else{
            //aspetta il tempo necessario ad arrivare alla data richiesta
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
    
     @Override
    public String toString(){
        return "Date";
    }

}