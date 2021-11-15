package PasswordVerification;

public interface ErrorHandlerStrategy {
    void handleErrors(Verification.Result result);
}
