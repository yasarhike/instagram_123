package org.insta.content.exception.story;

public final class StoryCreationFailedException extends StoryException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryCreationFailedException(final String message) {
        super(message);
    }
}
