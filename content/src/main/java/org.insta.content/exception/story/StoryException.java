package org.insta.content.exception.story;

import org.insta.exception.InstagramException;

public class StoryException extends InstagramException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryException(final String message) {
        super(message);
    }
}
