package org.insta.content.exception.story.storyshare;

import org.insta.content.exception.story.StoryException;

public class StoryShareFailedException extends StoryException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryShareFailedException(final String message) {
        super(message);
    }
}
