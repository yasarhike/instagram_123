package org.insta.content.exception.reel;

import org.insta.exception.InstagramException;

/**
 * <p>
 * Exception class for handle reel exceptions.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see InstagramException
 */
public class ReelException extends InstagramException {

    /**
     * <p>
     * Constructs an ReelException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelException(final String message) {
        super(message);
    }
}
