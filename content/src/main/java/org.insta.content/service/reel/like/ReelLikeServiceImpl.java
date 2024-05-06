package org.insta.content.service.reel.like;

import org.insta.content.dao.reel.like.ReelLikeDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Service implementation for managing reel liking operations.
 * </p>
 *
 * <p>
 * This class provides methods for adding and removing likes on reels, allowing users to like and unlike reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelLikeService
 * @see ReelLikeDAOImpl
 * @see ObjectValidator
 */
public class ReelLikeServiceImpl implements ReelLikeService{

    private static ReelLikeService reelLikeService;
    private final ReelLikeDAOImpl reelLikeDAOImpl;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelLikeServiceImpl() {
        reelLikeDAOImpl = ReelLikeDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelLikeServiceImpl class.
     * </p>
     *
     * @return The singleton instance of ReelLikeServiceImpl class.
     */
    public static ReelLikeService getInstance() {
        return reelLikeService == null ? new ReelLikeServiceImpl() : reelLikeService;
    }

    /**
     * <p>
     * Add a like for the particular reel.
     * </p>
     *
     * @param reelId Refers to the ID of the reel.
     * @param userId Refers to the ID of the user.
     * @return A byte array representing the result of the operation.
     */
    @Override
    public byte[] reelLike(int reelId, int userId) {
        return objectValidator.forSuccessResponse(reelLikeDAOImpl.reelLike(reelId, userId), new byte[]{});
    }

    /**
     * <p>
     * Remove a like for the particular reel.
     * </p>
     *
     * @param id Refers to the ID of the user.
     * @return A byte array representing the result of the operation.
     */
    @Override
    public byte[] reelUnlike(int id) {
        return objectValidator.manualResponse(reelLikeDAOImpl.reelUnlike(id));
    }
}
