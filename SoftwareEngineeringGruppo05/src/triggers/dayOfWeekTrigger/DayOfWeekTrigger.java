package triggers.dayOfWeekTrigger;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import triggers.timeTrigger.TimeTrigger;
import triggers.Trigger;

/**
 *
 * @author Luca
 */
public class DayOfWeekTrigger extends Observable implements Trigger {

    private DayOfWeek dayOfWeek; //giorno della settimana in cui scatta il trigger
    private boolean repetition;
    private boolean verified;

    private static final int MILLISECONDS_DAY = 86400000; //millisecondi in un giorno
    private static final int LENGHT_OF_WEEK = 7; //giorni in una settimana

    public DayOfWeekTrigger(DayOfWeek dayOfWeek, boolean repetition) {
        this.dayOfWeek = dayOfWeek;
        this.repetition = repetition;
        this.verified = false;
    }

    @Override
    public boolean isVerified() {
        return this.verified;
    }
    
    @Override
    public boolean isRepeated() {
        return this.repetition;
    }

    @Override
    public void checkTrigger() {

        verified = false;
        setChanged();
            
        if (LocalDateTime.now().getDayOfWeek().equals(dayOfWeek)) { //se il giorno della settimana corrisponde al giorno della settimana attuale
            verified = true;
            setChanged();
            notifyObservers();
        } else {
            //aspetta il tempo necessario ad arrivare al giorno della settimana richiesto
            long wait;
            int difference = dayOfWeek.getValue() - LocalDateTime.now().getDayOfWeek().getValue();
            if (difference > 0) {
                wait = (difference * MILLISECONDS_DAY) - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis();
            } else {
                wait = ((LENGHT_OF_WEEK + difference) * MILLISECONDS_DAY) - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis();
            }
            try {
                Thread.sleep(wait);
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        while (repetition) {
            //aspetta il tempo necessario ad arrivare nuovamente al giorno della settimana richiesto
            verified = false;
            setChanged();
            try {
                Thread.sleep(MILLISECONDS_DAY*LENGHT_OF_WEEK - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis());
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(DayOfWeekTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
     @Override
    public String toString(){
        return "DayOfWeek";
    }

}