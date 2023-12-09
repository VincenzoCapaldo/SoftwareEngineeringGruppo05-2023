package model.triggers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.triggers.Trigger;

/**
 *
 * @author Luca
 */
public class DayOfMonthTrigger extends Observable implements Trigger{

    private int dayOfMonth;
    private boolean repetition;
    private boolean verified;
   
    private static final int MILLISECONDS_DAY = 86400000; //millisecondi in un giorno

    public DayOfMonthTrigger(int dayOfMonth, boolean repetition) {
        this.dayOfMonth = dayOfMonth;
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
            
        if (LocalDate.now().getDayOfMonth() == this.dayOfMonth) {    
            verified = true;
            setChanged();
            notifyObservers();
        } else {
            long wait;
            int difference = this.dayOfMonth - LocalDate.now().getDayOfMonth();
            if(difference > 0){
                wait = (difference * MILLISECONDS_DAY) - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis();
            }else{
                wait = ((YearMonth.now().lengthOfMonth() + difference) * MILLISECONDS_DAY) - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis();
            }
            try {
                Thread.sleep(wait);
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(DayOfMonthTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        while (repetition) {
            verified = false;
            setChanged();
            try {
                Thread.sleep(YearMonth.now().lengthOfMonth() * MILLISECONDS_DAY - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis());
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(DayOfMonthTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
    }
    
     @Override
    public String toString(){
        return "DayOfMonth";
    }

}