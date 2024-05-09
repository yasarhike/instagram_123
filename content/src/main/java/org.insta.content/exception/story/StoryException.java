package org.insta.content.exception.story;

import org.insta.exception.InstagramException;

/**
 * <p>
 * Exception class for manage story.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see InstagramException
 */
public class StoryException extends InstagramException {

    /**
     * <p>
     * Constructs an StoryException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public StoryException(final String message) {
        super(message);
    }
}
