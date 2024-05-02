package org.insta.content.view.reelfeed;

import org.insta.content.controller.home.ReelsFeedController;
import org.insta.content.view.home.HomeView;
import org.apache.logging.log4j.LogManager;
import org.insta.authentication.model.User;
import org.insta.content.model.reel.Reel;
import org.insta.content.view.reel.comment.ReelCommentView;
import org.insta.content.view.reel.like.ReelLikeView;
import org.insta.content.view.reel.share.ReelShareView;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Managing reels feed.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelsFeedView {

    private static ReelsFeedView reelsManager;
    private final ReelsFeedController reelsFeedController;
    private final HomeView homeView;
    private final Scanner scanner;
    private static final Logger LOGGER = LogManager.getLogger(ReelsFeedView.class);

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private ReelsFeedView() {
        reelsFeedController = ReelsFeedController.getInstance();
        homeView = HomeView.getInstance();
        scanner = SingletonScanner.getInstance().getScanner();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelsFeedView class.
     * </p>
     *
     * @return The singleton instance of ReelsFeedView class.
     */
    public static ReelsFeedView getInstance() {
        return reelsManager == null ? reelsManager = new ReelsFeedView() : reelsManager;
    }

    /**
     * <p>
     * Displays reels to the user and manages user interactions with reel content.
     * </p>
     *
     * @param user The user for whom reels are displayed.
     */
    public void displayReels(final User user) {
        List<Reel> reels = reelsFeedController.getReels();

        for (final Reel reel : reels) {
            LOGGER.info(reel.toString());
        }

        showReelOptions(user);
    }

    public void showReelOptions(final User user) {
        LOGGER.info("Press 1 - Like \nPress 2 - Comment \nPress 3 - Share\nPress 4 - back");
        final int userChoice = Integer.parseInt(scanner.nextLine());

        switch (userChoice) {
            case 1:
                ReelLikeView.getInstance().reelLike(user);
                break;
            case 2:
                ReelCommentView.getInstance().addComment(user);
                break;
            case 3:
                ReelShareView.getInstance().shareReel(user);
                break;
            case 4:
                homeView.home(user);
                break;
            default:
                showReelOptions(user);
        }
    }
}
