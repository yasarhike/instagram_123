package org.insta.content.exception.reel;

import org.insta.exception.InstagramException;

public class ReelException extends InstagramException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelException(final String message) {
        super(message);
    }
}
