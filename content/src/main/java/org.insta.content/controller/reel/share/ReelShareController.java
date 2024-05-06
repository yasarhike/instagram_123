package org.insta.content.controller.reel.share;

import org.insta.content.dao.reel.share.ReelShareDAO;
import org.insta.content.dao.reel.share.ReelShareDAOImpl;

/**
 * <p>
 * Class for managing sharing operations for reels within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides methods for sharing and removing shares on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelShareDAOImpl
 */
public final class ReelShareController {

    private static ReelShareController reelShareController;
    private final ReelShareDAO reelShareDAOImpl;

    /**
     * <p>
     * Restrict object creation outside of the class.
     * </p>
     */
    private ReelShareController() {
        reelShareDAOImpl = ReelShareDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of the ReelShareController class.
     * </p>
     *
     * @return The singleton instance of the ReelShareController class.
     */
    public static ReelShareController getInstance() {
        return reelShareController == null ? reelShareController = new ReelShareController() : reelShareController;
    }

    /**
     * <p>
     * Shares a reel with the specified user.
     * </p>
     *
     * @param userId the ID of the user
     * @param reelId the ID of the reel
     * @return the ID of the shared reel if successful, otherwise -1
     */
    public int reelShare(final int userId, final int reelId) {
        return reelShareDAOImpl.reelShare(userId, reelId);
    }

    /**
     * <p>
     * Removes a shared reel.
     * </p>
     *
     * @param id the ID of the shared reel to be removed
     * @return true if the shared reel is removed successfully, otherwise false
     */
    public boolean removeShare(final int id) {
        return reelShareDAOImpl.removeShare(id);
    }
}
