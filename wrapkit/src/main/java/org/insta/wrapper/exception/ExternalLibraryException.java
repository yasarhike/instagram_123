package org.insta.wrapper.exception;

/**
 * <p>
 * Custom runtime exception class representing a parse failure.
 * </p>
 *
 * <p>
 * This exception can be thrown to indicate failure during parsing operations.
 * It extends {@link RuntimeException}, making it an unchecked exception.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see RuntimeException
 */
public final class ExternalLibraryException extends RuntimeException {

    /**
     * <p>
     * Constructs a ParseFailedException with the specified detail message.
     * </p>
     *
     * @param message Refers the exception message.
     */
    public ExternalLibraryException(final String message) {
        super(message);
    }
}
