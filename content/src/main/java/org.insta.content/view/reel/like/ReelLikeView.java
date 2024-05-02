package org.insta.content.view.reel.like;

import org.insta.authentication.model.User;
import org.insta.content.controller.reel.like.ReelLikeController;
import org.insta.content.view.reelfeed.ReelsFeedView;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <p>
 * Represents the view for managing reel likes.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelLikeView {

    private static ReelLikeView reelLikeView;
    private final ReelLikeController reelLikeController;
    private final Scanner scanner;
    private final ReelsFeedView reelsFeedView;
    private final Logger LOGGER = LogManager.getLogger(ReelLikeView.class);;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private ReelLikeView() {
        reelLikeController = ReelLikeController.getInstance();
        scanner = SingletonScanner.getInstance().getScanner();
        reelsFeedView = ReelsFeedView.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelLikeView class.
     * </p>
     *
     * @return The singleton instance of ReelLikeView class.
     */
    public static ReelLikeView getInstance() {
        return reelLikeView == null ? reelLikeView = new ReelLikeView() : reelLikeView;
    }

    /**
     * <p>
     * Get the details for the particular reel for like.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void reelLike(final User user) {
        int reelId = 0;
        LOGGER.info("Enter the reel id : ");

        try {
            reelId = Integer.parseInt(scanner.nextLine());
        } catch (Exception exception) {
            reelLike(user);
        }

        if (reelLikeController.reelLike(user.getUserId(), reelId) > 0) {
            LOGGER.debug("Like operation successful ");
            reelsFeedView.displayReels(user);
        } else {
            LOGGER.debug("Like operation failed");
            reelLike(user);
        }
    }

    /**
     * <p>
     * Get the details for the particular reel for unlike.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void reelUnlike(final User user) {
        LOGGER.info("Enter the reel id :");
        final int reelId = Integer.parseInt(scanner.nextLine());

        if (reelLikeController.reelUnlike(reelId)) {
            LOGGER.debug("Unlike operation successful ");
            reelsFeedView.displayReels(user);
        } else {
            LOGGER.info("Unlike operation failed");
            reelUnlike(user);
        }
    }
}
