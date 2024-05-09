package org.insta.content.exception.reel.reellike;

import org.insta.exception.InstagramException;

public final class ReelLikeCreationFailedException extends InstagramException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelLikeCreationFailedException(final String message) {
        super(message);
    }
}
