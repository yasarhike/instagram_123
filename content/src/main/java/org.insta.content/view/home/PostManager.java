package org.insta.content.view.home;

import org.apache.logging.log4j.LogManager;
import org.insta.authentication.model.User;
import org.insta.content.view.post.PostView;
import org.insta.content.view.reel.ReelView;
import org.insta.content.view.story.StoryView;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <p>
 * Manages different types of posts (posts, reels, stories) and navigation between them.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostManager {

    private static PostManager postManager;
    private final Scanner scanner;
    private final HomeView homeView;
    private final Logger  LOGGER = LogManager.getLogger(PostManager.class);;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private PostManager() {
        scanner = SingletonScanner.getInstance().getScanner();
        homeView = HomeView.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostManager class.
     * </p>
     *
     * @return The singleton instance of PostManager class.
     */
    public static PostManager getInstance() {
        return postManager == null ? postManager = new PostManager() : postManager;
    }

    /**
     * <p>
     * Displays the post management options and handles user actions.
     * </p>
     *
     * @param user Refers to the {@link User}
     */
    public void uploadPost(final User user) {
        int userChoice = 0;

        try {
            LOGGER.info("Press 1 - Post\nPress 2 - Reel\nPress 3 - Story\nPress 4 - Back");
            userChoice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException exception) {
            LOGGER.error("Enter a valid input");
            uploadPost(user);
        }

        switch (userChoice) {
            case 1:
                PostView.getInstance().add(user);
                uploadPost(user);
                break;
            case 2:
                ReelView.getInstance().add(user);
                uploadPost(user);
                break;
            case 3:
                StoryView.getInstance().add(user);
                uploadPost(user);
                break;
            case 4:
                homeView.home(user);
                break;
            default:
                uploadPost(user);
        }
    }
}
