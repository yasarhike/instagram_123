package org.insta.content.exception;

import org.insta.exception.InstagramException;

/**
 * <p>
 * Exception class for handle data fetch.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see InstagramException
 */
public final class FetchDataFailedException extends InstagramException {

    /**
     * <p>
     * Constructs an FetchDataFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public FetchDataFailedException(final String message) {
        super(message);
    }
}
