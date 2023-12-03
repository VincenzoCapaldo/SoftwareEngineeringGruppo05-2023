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
            try{
                /* controlla il trigger una volta */
                if(rule.getTrigger().checkTrigger())
                   rule.getAction().execute();

                /* controlla il trigger ripetutamente */
                while(rule.getRepeate() && rule.getSleeping().toMillis()!=0){
                        Thread.sleep(rule.getSleeping().toMillis());
                        rule.getAction().execute(); 
                }   
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRule.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}