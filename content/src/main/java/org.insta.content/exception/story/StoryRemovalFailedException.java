package org.insta.content.exception.story;

public final class StoryRemovalFailedException extends StoryException{

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryRemovalFailedException(final String message) {
        super(message);
    }
}
