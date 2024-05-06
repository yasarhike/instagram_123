package org.insta.authentication.exception;

import org.insta.exception.InstagramException;

/**
 * <p>
 * Exception class representing a failure in profile creation within the Instagram application.
 * This exception is typically thrown when there is an issue while creating a user profile.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see InstagramException
 */
public class ProfileCreationFailedException extends InstagramException {

    /**
     * <p>
     * Constructs a new ProfileCreationFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ProfileCreationFailedException(final String message) {
        super(message);
    }
}
