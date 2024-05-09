package org.insta.content.exception.reel;

import org.insta.exception.InstagramException;

public final class ReelRemovalFailedException extends InstagramException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelRemovalFailedException(final String message) {
        super(message);
    }
}
