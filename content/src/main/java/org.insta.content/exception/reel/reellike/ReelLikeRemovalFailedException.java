package org.insta.content.exception.reel.reellike;

import org.insta.exception.InstagramException;

public final class ReelLikeRemovalFailedException extends InstagramException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelLikeRemovalFailedException(final String message) {
        super(message);
    }
}
