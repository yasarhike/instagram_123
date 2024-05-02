package org.insta.content.view.home;

import org.apache.logging.log4j.LogManager;
import org.insta.authentication.model.User;
import org.insta.authentication.view.UserAccountView;
import org.insta.content.view.post.PostView;
import org.insta.content.view.reel.ReelView;
import org.insta.content.view.story.StoryView;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <p>
 * Managing user profile.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ProfileManager {

    private static ProfileManager profileManager;
    private final UserAccountView userAccountView;
    private final Scanner scanner;
    private final Logger LOGGER = LogManager.getLogger(ProfileManager.class);;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private ProfileManager() {
        scanner = SingletonScanner.getInstance().getScanner();
        userAccountView = UserAccountView.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ProfileManager class.
     * </p>
     *
     * @return The singleton instance of ProfileManager class.
     */
    public static ProfileManager getInstance() {
        return profileManager == null ? profileManager = new ProfileManager() : profileManager;
    }

    /**
     * <p>
     * Displays the user profile
     * </p>
     *
     * @param user {@link User} related to the user.
     */
    public void userProfile(final User user) {
        LOGGER.info(String.join(" ", "Press 1 - Edit profile ",
                "\nPress 2 - View post \nPress 3 - View reels \nPress 4 - View story \nPress 5 - back"));
        final int userChoice = Integer.parseInt(scanner.nextLine());

        switch (userChoice) {
            case 1:
                userAccountView.updateProfile(user);
                break;
            case 2:
                PostView.getInstance().display(user);
                break;
            case 3:
                ReelView.getInstance().display(user);
                break;
            case 4:
                StoryView.getInstance().display(user);
                break;
            case 5:
                HomeView.getInstance().home(user);
                break;
            default:
                userProfile(user);
        }
    }
}
