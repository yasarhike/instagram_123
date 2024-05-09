package org.insta.authentication.exception;

import org.insta.exception.InstagramException;

/**
 * <p>
 * Exception class representing a failure in profile retrieval within the Instagram application.
 * This exception is typically thrown when there is an issue while retrieving a user profile.
 * </p>
 *
 * <p>
 * This class extends the InstagramException class and is part of the exception hierarchy for Instagram-related exceptions.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see InstagramException
 */
public class ProfileRetrivalFailedException extends InstagramException {

    /**
     * <p>
     * Constructs a new ProfileRetrievalFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ProfileRetrivalFailedException(final String message) {
        super(message);
    }
}
