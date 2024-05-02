package org.insta.content.view.story.like;

import org.insta.consolescanner.SingletonScanner;
import org.insta.authentication.model.User;
import org.insta.content.controller.story.like.StoryLikeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <p>
 * Represents the view for managing story likes.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class StoryLikeView {

    private static StoryLikeView storyLikeView;
    private final StoryLikeController storyLikeController;
    private final Scanner scanner;
    private final Logger LOGGER = LogManager.getLogger(StoryLikeView.class);;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private StoryLikeView() {
        storyLikeController = StoryLikeController.getInstance();
        scanner = SingletonScanner.getInstance().getScanner();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryLikeView class.
     * </p>
     *
     * @return The singleton instance of StoryLikeView class.
     */
    public static StoryLikeView getInstance() {
        return storyLikeView == null ? storyLikeView = new StoryLikeView() : storyLikeView;
    }

    /**
     * <p>
     * Get the details for the particular story for like.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void storyLike(final User user) {
        LOGGER.info("Enter the story id : ");
        final int storyId = Integer.parseInt(scanner.nextLine());

        if (storyLikeController.storyLike(user.getUserId(), storyId) > 0) {
            LOGGER.debug("Like operation successful ");
        } else {
            LOGGER.debug("Like operation failed");
        }
    }

    /**
     * <p>
     * Get the details for the particular story for unlike.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void storyUnLike(final User user) {
        LOGGER.info("Enter the reel id :");
        final int reelId = Integer.parseInt(scanner.nextLine());

        if (storyLikeController.storyUnlike(reelId)) {
            LOGGER.debug("Unlike operation successful ");
        } else {
            LOGGER.info("Unlike operation failed");
        }
    }
}
