package org.insta.authentication.view;

import org.insta.authentication.model.User;
import org.insta.homeinterface.HomePage;
import org.apache.logging.log4j.LogManager;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <p>
 * Managing user authentication operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class AuthenticationView {

    private static AuthenticationView authenticationView;
    private final UserAccountView profileView;
    private HomePage homeView;
    private final Logger LOGGER = LogManager.getLogger(AuthenticationView.class);

    /**
     * <p>
     * Private constructor to initialize necessary components for authentication.
     * </p>
     */
    private AuthenticationView() {
        profileView = UserAccountView.getInstance();
        profileView.setHomePage(homeView);
    }

    /**
     * <p>
     * Create a singleton instance of Authentication class if it is not already created.
     * </p>
     *
     * @return Singleton instance of AuthenticationPage class.
     */
    public static AuthenticationView getInstance() {
        return authenticationView == null ? authenticationView = new AuthenticationView() : authenticationView;
    }

    /**
     * <p>
     * Displays the registration page and allows the user to register.
     * </p>
     */
  public void authentication() {
//        int userChoice = 0;
//
//        try {
//            LOGGER.info("Press 1 - create new account \nPress 2 - login\nPress 3 - exit");
//            userChoice = Integer.parseInt(scanner.nextLine());
//
//        } catch (NumberFormatException exception) {
//            LOGGER.error("Enter the valid input");
//            authentication();
//        }
//
//        switch (userChoice) {
//            case 1:
//                signUp();
//                break;
//            case 2:
//                signIn();
//                break;
//            case 3:
//                System.exit(0);
//            default:
//                authentication();
//        }
    }

    /**
     * <p>
     * Displays the registration page and allows the user to register.
     * </p>
     */
    public void signUp() {
        final int result = profileView.createProfile();

        if (result != 0) {
            LOGGER.debug("Account creation successful");
            homeView.homePage(new User());
        } else {
            signUp();
        }
    }

    /**
     * <p>
     * Displays the login page and allows the user to login.
     * </p>
     */
//    public void signIn() {
//        final User user = profileView.getProfile();
//
//        if (user != null) {
//            LOGGER.debug("Login successful");
//            homeView.homePage(user);
//        } else {
//            LOGGER.debug("Invalid credentials");
//            signIn();
//        }
//    }

    public void setHomePage(final HomePage homePage) {
        this.homeView = homePage;
    }
}
