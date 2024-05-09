package org.insta.content.exception.reel.reelcomment;

import org.insta.exception.InstagramException;

public final class ReelCommentFailedException extends InstagramException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelCommentFailedException(final String message) {
        super(message);
    }
}
