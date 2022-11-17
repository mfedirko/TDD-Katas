public class BadPasswordException extends RuntimeException {
    private Verification.Result verificationResult;

    public BadPasswordException(Verification.Result verificationResult) {
        super(verificationResult.toString());
        this.verificationResult = verificationResult;
    }

    public Verification.Result getVerificationResult() {
        return verificationResult;
    }
}
