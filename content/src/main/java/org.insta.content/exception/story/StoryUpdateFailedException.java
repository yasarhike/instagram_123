package org.insta.content.exception.story;

/**
 * <p>
 * Exception class for handle story update.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryException
 */
public final class StoryUpdateFailedException extends StoryException {

    /**
     * <p>
     * Constructs an StoryUpdateFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryUpdateFailedException(final String message) {
        super(message);
    }
}
