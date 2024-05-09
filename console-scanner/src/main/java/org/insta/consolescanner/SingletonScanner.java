package org.insta.consolescanner;

import java.util.Scanner;

/**
 * <p>
 * Singleton class for creating a shared instance of Scanner for reading user input from the console.
 * </p>
 *
 * <p>
 * This class provides a singleton instance of Scanner to facilitate reading user input from the console.
 * It ensures that only one instance of Scanner is created and shared across the application.
 * </p>
 *
 * <p>
 * The {@code getInstance()} method is used to retrieve the singleton instance of the SingletonScanner class.
 * </p>
 *
 * <p>
 * The {@code getScanner()} method returns the Scanner instance created by this class.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Scanner
 */

public final class SingletonScanner {

    private static SingletonScanner singletonScanner;
    private final Scanner scanner;

    /**
     * <p>
     * Private constructor to restrict instantiation from outside the class.
     * </p>
     */
    private SingletonScanner() {
        scanner = new Scanner(System.in);
    }

    /**
     * <p>
     * Creates singleton instance of SingletonScanner class if it is not created.
     * </p>
     *
     * @return Singleton instance of SingletonScanner.
     */
    public static SingletonScanner getInstance() {
        return singletonScanner == null ?  new SingletonScanner() : singletonScanner;
    }

    /**
     * Returns the scanner instance.
     *
     * @return {@link Scanner} Scanner instance.
     */
    public Scanner getScanner() {
        return this.scanner;
    }
}
