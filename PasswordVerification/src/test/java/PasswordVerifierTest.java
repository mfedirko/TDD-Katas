import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PasswordVerifierTest {
    private PasswordVerifier verifier = new PasswordVerifier();


    @Test(expected = BadPasswordException.class)
    public void minLength() {
        final int minLen = 9;
        String pwd = String.join("", Collections.nCopies(minLen - 1, "a"));

        verifier.verify(pwd);
    }
    @Test
    public void minLength_descriptiveMsg() {
        try {
            verifier.verify("a1");
        } catch (BadPasswordException ex) {
            assertThat(ex.getMessage(), containsString("length"));
        }
    }
    @Test(expected = BadPasswordException.class)
    public void notNull() {
        verifier.verify(null);
    }
    @Test
    public void notNull_descriptiveMsg() {
        try {
            verifier.verify(null);
        } catch (BadPasswordException ex) {
            assertThat(ex.getMessage(), containsString("null"));
        }
    }
    @Test(expected = BadPasswordException.class)
    public void atLeastOneUpperCaseLetter() {
        verifier.verify("abc");
    }
    @Test
    public void atLeastOneUpperCaseLetter_descriptiveMsg() {
        try {
            verifier.verify("abc");
        } catch (BadPasswordException ex) {
            assertThat(ex.getMessage(), anyOf(containsString("uppercase"), containsString("A-Z"), containsString("upper-case")));
        }
    }
    @Test(expected = BadPasswordException.class)
    public void atLeastOneLowerCaseLetter() {
        verifier.verify("ABC");
    }
    @Test
    public void atLeastOneLowerCaseLetter_descriptiveMsg() {
        try {
            verifier.verify("ABC");
        } catch (BadPasswordException ex) {
            assertThat(ex.getMessage(), anyOf(containsString("lowercase"), containsString("a-z"), containsString("lower-case")));
        }
    }
    @Test(expected = BadPasswordException.class)
    public void atLeastOneNumber() {
        verifier.verify("ABC");
    }
    @Test
    public void atLeastOneNumber_descriptiveMessage() {
        try {
            verifier.verify("abc");
        } catch (BadPasswordException ex) {
            assertThat(ex.getMessage(), anyOf(containsString("number"), containsString("digit"), containsString("0-9")));
        }
    }
    @Test
    public void whenAtLeastThreeRulesSatisfied_thenOk() {
        verifier.verify("abcDE123");
    }
    @Test(expected = BadPasswordException.class)
    public void minLowercaseRuleIsRequired() {
        verifier.verify("ABC32312345");
    }
    @Test
    public void doesNotProceedBeyondThreeRules() {
        verifier = new PasswordVerifier(new NoopErrorHandlerStrategy());
        Verification.Result result = verifier.verify("abcDE123");
        assertEquals(3, result.getPassedCount());
        assertEquals(0, result.getErrors().size());
    }

    private static class NoopErrorHandlerStrategy implements ErrorHandlerStrategy {
        @Override
        public void handleErrors(Verification.Result result) {
            // no-op
        }
    }
}
