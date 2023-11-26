package rules;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paolo
 */
public class ThreadRule implements Runnable {
    private Rule rule;

    public ThreadRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public void run() {
        /*check trigger one time*/
        if(rule.getTrigger().checkTrigger())
           rule.getAction().execute();
        
        /*verify repetition and sleeping settings. If sleeping is 0 millis repeat rule each 10 millis*/
        long newSleeping=rule.getSleeping().toMillis();
        if(rule.getRepeate()&&newSleeping==0){
            newSleeping=10000;
        }
        
        /*repetition*/
        while(rule.getRepeate()){
            try {
                Thread.sleep(newSleeping);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRule.class.getName()).log(Level.SEVERE, null, ex);
            }
            rule.getAction().execute();
        }

    }

}
