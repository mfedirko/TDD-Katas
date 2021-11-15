package PasswordVerification;

public class ThrowErrorHandlerStrategy implements ErrorHandlerStrategy {
    @Override
    public void handleErrors(Verification.Result result) {
        throw new BadPasswordException(result);
    }
}
