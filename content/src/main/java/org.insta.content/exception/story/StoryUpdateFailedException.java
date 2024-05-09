package org.insta.content.exception.story;

public final class StoryUpdateFailedException extends StoryException{

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryUpdateFailedException(final String message) {
        super(message);
    }
}
