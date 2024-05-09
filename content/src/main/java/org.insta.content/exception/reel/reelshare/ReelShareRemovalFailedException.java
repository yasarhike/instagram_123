package org.insta.content.exception.reel.reelshare;

import org.insta.content.exception.reel.ReelException;

public final class ReelShareRemovalFailedException extends ReelException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelShareRemovalFailedException(final String message) {
        super(message);
    }
}
