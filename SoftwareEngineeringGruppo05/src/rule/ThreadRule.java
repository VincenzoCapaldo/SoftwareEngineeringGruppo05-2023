package rule;

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
        rule.getTrigger().checkTrigger(); //fa partire il controllo del trigger
    }

}