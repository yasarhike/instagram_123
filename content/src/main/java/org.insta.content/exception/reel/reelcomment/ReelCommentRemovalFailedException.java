package org.insta.content.exception.reel.reelcomment;

import org.insta.content.exception.reel.ReelException;

public final class ReelCommentRemovalFailedException extends ReelException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelCommentRemovalFailedException(final String message) {
        super(message);
    }
}
