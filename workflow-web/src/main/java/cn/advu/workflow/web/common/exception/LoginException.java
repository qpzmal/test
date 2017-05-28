package cn.advu.workflow.web.common.exception;

/**
 * Created by kai
 */
public class LoginException extends RuntimeException {
    public LoginException() {
        super();
    }

    public LoginException(String s) {
        super(s);
    }

    public LoginException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public LoginException(Throwable throwable) {
        super(throwable);
    }
}
