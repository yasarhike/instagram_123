package org.insta.content.controller.home;

import org.insta.content.model.reel.Reel;
import org.insta.content.dao.reel.reelfeed.ReelFeedServiceDAO;

import java.util.List;

/**
 * <p>
 * Controller class responsible for managing reels feed.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelsFeedController {

    private static ReelsFeedController reelsManagerController;
    private final ReelFeedServiceDAO reelFeedServiceDAO;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelsFeedController() {
        reelFeedServiceDAO = ReelFeedServiceDAO.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelFeedController class.
     * </p>
     *
     * @return The singleton instance of ReelFeedController class.
     */
    public static ReelsFeedController getInstance() {
        return reelsManagerController == null ? reelsManagerController = new ReelsFeedController() : reelsManagerController;
    }

    /**
     * <p>
     * Gets the reels for all user.
     * </p>
     */
    public List<Reel> getReels() {
        return reelFeedServiceDAO.getReels();
    }


    /**
     * <p>
     * Gets the comments for all user.
     * </p>
     */
//    public List<Comment> getComment(final int postId) {
//        return reelFeedServiceDAO.getComment(postId);
//    }
}
