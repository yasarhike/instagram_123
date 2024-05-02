package org.insta.content.view.home;

import org.apache.logging.log4j.LogManager;
import org.insta.authentication.model.User;
import org.insta.authentication.view.AuthenticationView;
import org.insta.content.view.reelfeed.ReelsFeedView;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.Logger;
import org.insta.homeinterface.HomePage;

import java.util.Scanner;

/**
 * <p>
 * Represents the view for home page.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class HomeView implements HomePage{

    private static HomeView homeView;
    private final Scanner scanner;
    private final Logger LOGGER = LogManager.getLogger(HomeView.class);;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private HomeView() {
        scanner = SingletonScanner.getInstance().getScanner();
    }

    /**
     * <p>
     * Returns the singleton instance of HomeView class.
     * </p>
     *
     * @return The singleton instance of HomeView class.
     */
    public static HomeView getInstance() {

        return homeView == null ? homeView = new HomeView() : homeView;
    }

    /**
     * <p>
     * Displays the home screen and handles user actions.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void home(final User user) {
        int userChoice = 0;

        try {
            LOGGER.info("Press 1 - Upload\nPress 2 - Reels\nPress 3 - Profile\nPress 4 - Logout");
            userChoice = Integer.parseInt(scanner.nextLine());
        } catch (final NumberFormatException exception) {
            LOGGER.error("Enter a valid input");
            home(user);
        }

        switch (userChoice) {
            case 1:
                PostManager.getInstance().uploadPost(user);
                break;
            case 2:
                ReelsFeedView.getInstance().displayReels(user);
                break;
            case 3:
                ProfileManager.getInstance().userProfile(user);
                break;
            case 4:
                AuthenticationView.getInstance().authentication();
                break;
            default:
                LOGGER.error("Enter a valid input");
                home(user);
        }
    }

    @Override
    public void homePage(Object object) {
        home((User) object);
    }
}
