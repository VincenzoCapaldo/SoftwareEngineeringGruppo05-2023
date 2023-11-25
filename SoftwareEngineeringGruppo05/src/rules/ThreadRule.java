package rules;

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
        if(rule.getTrigger().checkTrigger())
            rule.getAction().execute();
    }

}
