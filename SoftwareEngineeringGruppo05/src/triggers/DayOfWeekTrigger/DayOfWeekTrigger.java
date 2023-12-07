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
    private static final int LENGHT_OF_WEEK = 7;

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
        DayOfWeek actualDayOfWeek = LocalDateTime.now().getDayOfWeek();

        if (actualDayOfWeek.equals(dayOfWeek)) {
            verified = true;
            setChanged();
            notifyObservers();
        } else {
            long wait;
            long difference = dayOfWeek.getValue() - actualDayOfWeek.getValue();
            if (difference > 0) {
                wait = ((difference) * MILLISECONDS_DAY) - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis();
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
        
        verified = false;

        while (repetition) {
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

    
}
