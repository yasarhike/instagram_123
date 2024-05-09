package org.insta.content.controller.reel.like;

import org.insta.content.service.reel.like.ReelLikeServiceImpl;

/**
 * <p>
 * Implemented class for managing reel like operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelLikeServiceImpl
 * @see ReelLikeServiceImpl
 */
public final class ReelLikeController {

    private static ReelLikeController reelLikeController;
    private final ReelLikeServiceImpl reelLikeService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelLikeController() {
        reelLikeService = ReelLikeServiceImpl.getInstance();
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
     * Adds a like for a specific reel.
     * </p>
     *
     * @param userId the ID of the user
     * @param reelId the ID of the reel
     * @return the response in byte array format
     */
    public byte[] reelLike(final int userId, final int reelId) {
        return reelLikeService.reelLike(userId, reelId);
    }

    /**
     * <p>
     * Removes a like for a specific reel.
     * </p>
     *
     * @param id the ID of the like to be removed
     * @return the response in byte array format
     */
    public byte[] reelUnlike(final int id) {
        return reelLikeService.reelUnlike(id);
    }
}
