package org.insta.authentication.view;

import org.insta.authentication.controller.UserAccountController;
import org.insta.authentication.model.User;
import org.insta.homeinterface.HomePage;
import org.apache.logging.log4j.LogManager;
import org.insta.authentication.view.inputhandler.UserInputHandler;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <p>
 * Managing user account operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */

public final class UserAccountView {

    private static UserAccountView profileView;
    private final UserInputHandler userInputHandler;
    private final Scanner scanner;
    private final UserAccountController userAccountController;
    private HomePage homePage;
    private static final Logger LOGGER = LogManager.getLogger(UserAccountView.class);

    /**
     * <p>
     * Private constructor to initialize necessary components for account creation.
     * </p>
     */
    private UserAccountView() {
        userAccountController = UserAccountController.getInstance();
        userInputHandler = UserInputHandler.getInstance();
        scanner = SingletonScanner.getInstance().getScanner();
    }

    /**
     * <p>
     * Create a singleton instance of UserAccountView class if it is not already created.
     * </p>
     *
     * @return Singleton instance of UserAccountView class.
     */
    public static UserAccountView getInstance() {
        return profileView == null ? profileView = new UserAccountView() : profileView;
    }

    /**
     * <p>
     * Displays the account creation page and allows the user to create an account.
     * </p>
     */
    public int createProfile() {
        final User user = new User();

        user.setName(userInputHandler.getName());
        user.setPassword(userInputHandler.getPassword());
        userInputHandler.getMobileOrEmail(user);

        return userAccountController.createProfile(user).length;
    }

//    public User getProfile() {
//        Key key = null;
//        try {
//            LOGGER.info("Press 1 - Login via mobile \nPress 2 - Login via email\nPres 3 - Login via name");
//            key = Key.getKey(Integer.parseInt(scanner.nextLine()));
//
//        } catch (NumberFormatException numberFormatException) {
//            getProfile();
//        }
//
//        LOGGER.info("Enter the emailOrMobileOrUserName");
//        final String emailOrMobileOrName = scanner.nextLine().trim();
//
//        LOGGER.info("Enter the password :");
//        final String password = scanner.nextLine().trim();
//
//        return userAccountController.getProfile(key.id);
//    }

    /**
     * <p>
     * Redirects to the authentication page if the back symbol is entered.
     * </p>
     */
    public void back(final String symbol) {
        if (symbol.matches("^#")) {
            AuthenticationView.getInstance().authentication();
        }
    }

    public void updateProfile(final User user) {
        User updateUser = new User();
        updateUser.setUserId(user.getUserId());

        LOGGER.info("Want to edit the name (Enter yes or no) :");
        updateUser.setName(scanner.nextLine().equals("yes") ? userInputHandler.getName() : null);

        LOGGER.info("Want to edit the email (Enter yes or no) :");
        updateUser.setEmail(scanner.nextLine().equals("yes") ? userInputHandler.getEmail() : null);

        LOGGER.info("Want to edit the password (Enter yes or no) :");
        updateUser.setPassword(scanner.nextLine().equals("yes") ? userInputHandler.getPassword() : null);

        LOGGER.info("Want to edit the door number (Enter yes or no) :");
        updateUser.getAddress().setDoorNumber(scanner.nextLine().equals("yes") ? userInputHandler.getDoorNumber() : 0);

        if (userAccountController.updateProfile(updateUser).length > 0) {
            localUpdate(user, updateUser);
            LOGGER.info("Update successful");
            homePage.homePage(user);
        } else {
            LOGGER.info("Update failed");
            homePage.homePage(user);
        }
    }

    public void deleteProfile(final User user) {
        if (userAccountController.deleteProfile(user.getUserId())) {
            LOGGER.debug("Account deleted successfully");
        } else {
            LOGGER.debug("Account deletion failed");
        }
    }

    public void localUpdate(final User user, final User updateUser) {
        user.setName(updateUser.getName() != null ? updateUser.getName() : user.getName());
        user.setMobileNumber(updateUser.getMobileNumber()
                != null ? updateUser.getMobileNumber() : user.getMobileNumber());
        user.setPassword(updateUser.getPassword() != null ? updateUser.getPassword() : user.getPassword());
        user.setEmail(updateUser.getEmail() != null ? updateUser.getEmail() : user.getEmail());
        user.getAddress().setState(updateUser.getAddress().getState()
                != null ? updateUser.getAddress().getState() : user.getAddress().getState());
        user.getAddress().setDoorNumber(updateUser.getAddress().getDoorNumber()
                != 0 ? updateUser.getAddress().getDoorNumber() : user.getAddress().getDoorNumber());
        user.getAddress().setStreetName(updateUser.getAddress().getStreetName()
                != null ? updateUser.getAddress().getStreetName() : user.getAddress().getStreetName());
    }

    public void setHomePage(final HomePage homePage) {
        this.homePage = homePage;
    }
}
