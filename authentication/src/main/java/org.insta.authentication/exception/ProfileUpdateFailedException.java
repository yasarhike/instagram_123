package org.insta.authentication.exception;

import org.insta.exception.UserException;

/**
 * <p>
 * Exception class for handle failed profile update.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class ProfileUpdateFailedException extends UserException {
    public ProfileUpdateFailedException(final String message) {
        super(message);
    }
}
