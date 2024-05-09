package org.insta.content.exception.story.storylike;

import org.insta.content.exception.story.StoryException;

public final class StoryLikeCreationFailedException extends StoryException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryLikeCreationFailedException(final String message) {
        super(message);
    }
}
