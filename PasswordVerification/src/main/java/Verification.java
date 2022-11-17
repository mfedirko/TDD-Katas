import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Verification {
    private final List<Rule> rules;
    private final int stopAtPassedCount;

    public Verification(List<Rule> rules, int stopAtPassedCount) {
        this.rules = rules;
        this.stopAtPassedCount = stopAtPassedCount;
    }


    public static VerificationBuilder builder() {
        return new VerificationBuilder();
    }

    public static class VerificationBuilder {
        private List<Rule> rules = new ArrayList<>();
        private int stopAtPassedCount = Integer.MAX_VALUE;

        public VerificationBuilder addRule(Rule rule) {
            this.rules.add(rule);
            return this;
        }

        public VerificationBuilder setStopAtPassedCount(int stopAtPassedCount) {
            this.stopAtPassedCount = stopAtPassedCount;
            return this;
        }

        public Verification build() {
            return new Verification(rules, stopAtPassedCount);
        }
    }


    public Result verify(String input) {
        Result res = new Result();
        for (Rule rule : rules) {
            boolean passed = rule.checkPasses(input);
            if (passed) {
                res.addPassedRule(rule);
                if (res.getPassedCount() >= stopAtPassedCount) break;
            } else {
                res.addFailedRule(rule);
                if (rule.isMandatory()) break;
            }
        }
        return res;
    }

    public static class Result {
        private List<Rule> rulesFailed = new ArrayList<>();
        private List<Rule> rulesPassed = new ArrayList<>();

        public int getPassedCount() {
            return rulesPassed.size();
        }

        public List<Rule> getRulesFailed() {
            return new ArrayList<>(rulesFailed);
        }

        public List<Rule> getRulesPassed() {
            return new ArrayList<>(rulesPassed);
        }

        public List<String> getErrors() {
            return rulesFailed.stream()
                    .map(Rule::getDescription)
                    .collect(Collectors.toList());
        }

        public boolean hasFailed() {
            return !rulesFailed.isEmpty();
        }

        private void addFailedRule(Rule rule) {
            this.rulesFailed.add(rule);
        }
        private void addPassedRule(Rule rule) {
            this.rulesPassed.add(rule);
        }

        @Override
        public String toString() {
            return "Rules passed = " + getPassedCount() + ". Errors = " + getErrors();
        }

    }
}
