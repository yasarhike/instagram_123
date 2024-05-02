package org.insta.authentication.exception;

import org.insta.exception.UserException;

/**
 * <p>
 * Exception class for handle failed profile retrival.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class ProfileRetrivalFailedException extends UserException {
    public ProfileRetrivalFailedException(final String message) {
        super(message);
    }
}
