package org.insta.content.controller.post.like;

import org.insta.content.dao.post.like.PostLikeDAOImpl;

/**
 * <p>
 * Implemented class for managing post comment operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostLikeController {

    private static PostLikeController postLikeController;
    private final PostLikeDAOImpl postLikeDAOImpl;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostLikeController() {
        postLikeDAOImpl = PostLikeDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeController class.
     * </p>
     *
     * @return The singleton instance of PostLikeController class.
     */
    public static PostLikeController getInstance() {
        return postLikeController == null ? postLikeController = new PostLikeController() : postLikeController;
    }

    /**
     * <p>
     * Adds a like for the specified post.
     * </p>
     *
     * @param userId Refer to the userId of the user.
     * @param postId Refer the postId of the post.
     * @return True if the like is added successfully, otherwise false.
     */
    public int postLike(final int userId, final int postId) {
        return postLikeDAOImpl.postLike(userId, postId);
    }

    /**
     * <p>
     * Removes a like for the specified post.
     * </p>
     *
     * @param userId Refer to the userId of the user.
     * @param postId Refer the postId of the post.
     * @return True if the like is removed successfully, otherwise false.
     */
    public boolean postUnlike(final int userId, final int postId) {
        return postLikeDAOImpl.postUnlike(userId, postId);
    }
}
