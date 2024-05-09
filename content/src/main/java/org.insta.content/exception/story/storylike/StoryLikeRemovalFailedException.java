package org.insta.content.exception.story.storylike;

import org.insta.content.exception.story.StoryException;

public final class StoryLikeRemovalFailedException extends StoryException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryLikeRemovalFailedException(final String message) {
        super(message);
    }
}
