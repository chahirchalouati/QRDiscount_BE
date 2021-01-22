/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Exceptions.EntityExceptions;

/**
 *
 * @author Chahir Chalouati
 */
public class PasswordInvalidException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public PasswordInvalidException() {
    }

    public PasswordInvalidException(String message) {
        super(message);
    }

    public PasswordInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordInvalidException(Throwable cause) {
        super(cause);
    }

    public PasswordInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
