package org.insta.content.exception.reel;

public final class ReelUpdateFailedException extends ReelException{

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelUpdateFailedException(final String message) {
        super(message);
    }
}
