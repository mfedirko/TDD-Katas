import java.util.Objects;

public class PasswordVerifier {

    public static final int MIN_PASS_LEN = 9;
    public static final int MIN_RULES_PASSED = 3;

    private final Verification verification;
    private final ErrorHandlerStrategy errorHandlerStrategy;

    public PasswordVerifier() {
        this(new ThrowErrorHandlerStrategy());
    }
    public PasswordVerifier(ErrorHandlerStrategy errorHandlerStrategy) {
        this.errorHandlerStrategy = Objects.requireNonNull(errorHandlerStrategy);
        this.verification = Verification.builder()
            .setStopAtPassedCount(MIN_RULES_PASSED)
            .addRule(new Rule(Objects::nonNull, "Password cannot be null", true))
            .addRule(new Rule(pwd -> pwd.matches(".*[a-z].*"), "Password must contain at least one lowercase letter", true))
            .addRule(new Rule(pwd -> pwd.matches(".*[0-9].*"), "Password must contain at least one digit"))
            .addRule(new Rule(pwd -> pwd.length() >= MIN_PASS_LEN, "Password length must be at least " + MIN_PASS_LEN))
            .addRule(new Rule(pwd -> pwd.matches(".*[A-Z].*"), "Password must contain at least one uppercase letter"))
            .build();
    }

    public Verification.Result verify(String password) {
        Verification verification = getVerification();
        Verification.Result result = verification.verify(password);
        if (result.hasFailed()) {
            errorHandlerStrategy.handleErrors(result);
        }
        return result;
    }

    private Verification getVerification() {
        return verification;
    }
}
