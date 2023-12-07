/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triggers.DayOfWeekTrigger;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import triggers.TimeTrigger.TimeTrigger;
import triggers.Trigger;

/**
 *
 * @author Luca
 */
public class DayOfWeekTrigger extends Observable implements Trigger {

    DayOfWeek dayOfWeek;
    private boolean repetition;
    private boolean verified;
    private static final int MILLISECONDS_DAY = 86400000; //millisecondi in un giorno

    public DayOfWeekTrigger(DayOfWeek dayOfWeek, boolean repetition) {
        this.dayOfWeek = dayOfWeek;
        this.repetition = repetition;
        this.verified = false;
    }

    @Override
    public boolean isVerified() {
        return verified;
    }

    @Override
    public void checkTrigger() {


        verified = false;
        DayOfWeek actualDayOfWeek = LocalDateTime.now().getDayOfWeek();

        if (actualDayOfWeek.equals(dayOfWeek)) {    //caso oggi lunedì e 
            verified = true;
            setChanged();
            notifyObservers();
        } else {
            long wait;
            long difference = dayOfWeek.getValue() - actualDayOfWeek.getValue();
            long toMidnight = this.toMidnight();
            if (difference > 0) {
                wait = ((difference - 1) * MILLISECONDS_DAY) + toMidnight;
            } else {
                wait = ((6 + difference) * MILLISECONDS_DAY) + toMidnight;
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
        
        verified = false;

        while (repetition) {
            try {
                Thread.sleep(MILLISECONDS_DAY*7 - this.toMidnight());
            } catch (InterruptedException ex) {
                Logger.getLogger(DayOfWeekTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private long toMidnight(){
        return Duration.between(LocalTime.now(), LocalTime.MIDNIGHT).toMillis();
    }

    @Override
    public boolean isRepeated() {
        return repetition;
    }
    
}
