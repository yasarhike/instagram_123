package org.insta.content.exception.story;

public final class StoryRetrivalFailedException extends StoryException{

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryRetrivalFailedException(final String message) {
        super(message);
    }
}
