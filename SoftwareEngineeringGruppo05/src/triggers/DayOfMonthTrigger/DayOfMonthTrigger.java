/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triggers.DayOfMonthTrigger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import triggers.Trigger;

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
        
        if (LocalDate.now().getDayOfMonth() == this.dayOfMonth) {    
            verified = true;
            setChanged();
            notifyObservers();
        } else {
            long wait;
            long difference = this.dayOfMonth - LocalDate.now().getDayOfMonth();
            if(difference > 0){
                wait = ((difference) * MILLISECONDS_DAY) - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis();
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
        
        verified = false;

        while (repetition) {
            try {
                Thread.sleep(YearMonth.now().lengthOfMonth()*MILLISECONDS_DAY - Duration.between(LocalTime.MIDNIGHT, LocalTime.now()).toMillis());
                verified = true;
                setChanged();
                notifyObservers();
            } catch (InterruptedException ex) {
                Logger.getLogger(DayOfMonthTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        
    }


}
