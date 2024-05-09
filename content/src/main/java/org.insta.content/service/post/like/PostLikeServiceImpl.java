package org.insta.content.service.post.like;

import org.insta.content.dao.post.like.PostLikeDAO;
import org.insta.content.dao.post.like.PostLikeDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Managing user post like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ObjectValidator
 * @see PostLikeDAO
 */
public final class PostLikeServiceImpl implements PostLikeService {

    private static PostLikeService postLikeService;
    private final PostLikeDAO postLikeDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostLikeServiceImpl() {
        postLikeDAO = PostLikeDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeServiceImpl class.
     * </p>
     *
     * @return The singleton instance of PostLikeServiceImpl class.
     */
    public static PostLikeService getInstance() {
        return postLikeService == null ? new PostLikeServiceImpl() : postLikeService;
    }

    /**
     * <p>
     * Adds a like for the particular post.
     * </p>
     *
     * @param userId the ID of the user
     * @param postId the ID of the post
     * @return a byte array representing the result of the operation
     */
    public byte[] postLike(final int userId, final int postId) {
        return objectValidator.forSuccessResponse(postLikeDAO.postLike(userId, postId), new byte[]{});
    }

    /**
     * <p>
     * Removes a like for a particular post.
     * </p>
     *
     * @param postId the ID of the post
     * @return a byte array representing the result of the operation
     */
    public byte[] postUnlike(final int postId) {
        return objectValidator.manualResponse(postLikeDAO.postUnlike(postId));
    }
}
