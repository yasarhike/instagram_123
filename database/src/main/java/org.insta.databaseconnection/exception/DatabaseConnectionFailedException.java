package org.insta.databaseconnection.exception;

import org.insta.exception.InstagramException;

public final class DatabaseConnectionFailedException extends InstagramException {

    /**
     * <p>
     * Constructs an InstagramException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public DatabaseConnectionFailedException(final String message) {
        super(message);
    }
}
