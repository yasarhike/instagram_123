package org.insta.consolescanner;

import java.util.Scanner;

/**
 * <p>
 * Singleton class for creating a shared instance of Scanner for reading user input from the console
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
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
        return singletonScanner == null ? singletonScanner = new SingletonScanner() : singletonScanner;
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
