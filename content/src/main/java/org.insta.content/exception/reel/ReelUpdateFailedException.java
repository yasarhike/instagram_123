package org.insta.content.exception.reel;

/**
 * <p>
 * Exception class for handle reel update.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelException
 */
public final class ReelUpdateFailedException extends ReelException {

    /**
     * <p>
     * Constructs an ReelUpdateFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ReelUpdateFailedException(final String message) {
        super(message);
    }
}
