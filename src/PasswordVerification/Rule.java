package PasswordVerification;

import java.util.Objects;
import java.util.function.Predicate;

class Rule {
    private final Predicate<String> predicate;
    private final String description;
    private final boolean mandatory;

    Rule(Predicate<String> predicate, String description) {
        this(predicate, description, false);
    }
    Rule(Predicate<String> predicate, String description, boolean mandatory) {
        this.predicate = Objects.requireNonNull(predicate);
        this.description = description;
        this.mandatory = mandatory;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public boolean checkPasses(String input) {
        return predicate.test(input);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Rule(" +
                "description='" + description + '\'' +
                ')';
    }
}
