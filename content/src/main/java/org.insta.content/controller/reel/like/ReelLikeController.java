package org.insta.content.controller.reel.like;

import org.insta.content.dao.reel.like.ReelLikeDAO;

/**
 * <p>
 * Implemented class for managing reel like operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelLikeController {

    private static ReelLikeController reelLikeController;
    private final ReelLikeDAO reelLikeDAO;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelLikeController() {
        reelLikeDAO = ReelLikeDAO.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelLikeController class.
     * </p>
     *
     * @return The singleton instance of ReelLikeController class.
     */
    public static ReelLikeController getInstance() {
        return reelLikeController == null ? reelLikeController = new ReelLikeController() : null;
    }

    /**
     * <p>
     * Add like for a specific reel.
     * </P>
     *
     * @param userId Refer to id of the user.
     * @param reelId Refer the id of the reel.
     * @return True if the post is updated successfully, otherwise false.
     */
    public int reelLike(final int userId, final int reelId) {
        return reelLikeDAO.reelLike(userId, reelId);
    }

    /**
     * <p>
     * Removes a like for a specific reel.
     * </P>
     *
     * @param userId Refers the id of the user.
     * @param reelId Refers the id of the reel.
     * @return True if the post is updated successfully, otherwise false.
     */
    public boolean reelUnlike(final int id) {
        return reelLikeDAO.reelUnlike(id);
    }
}
