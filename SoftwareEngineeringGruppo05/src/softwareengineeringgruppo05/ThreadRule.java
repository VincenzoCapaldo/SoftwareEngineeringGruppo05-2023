package softwareengineeringgruppo05;

import rules.Rule;

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
        rule.checkRule();
    }

}
