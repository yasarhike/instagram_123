package org.insta.content.service.post.share;

import org.insta.content.dao.post.share.PostShareDAO;
import org.insta.content.dao.post.share.PostShareDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Manage post share.
 * </p>
 *
 * @see PostShareService
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostShareServiceImpl implements PostShareService{

    private static PostShareService postShareService;
    private final PostShareDAO postShareDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostShareServiceImpl() {
        postShareDAO = PostShareDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostShareServiceImpl class.
     * </p>
     *
     * @return The singleton instance of PostShareServiceImpl class.
     */
    public static PostShareService getInstance() {
        return postShareService == null ? new PostShareServiceImpl() : postShareService;
    }

    /**
     * <p>
     * Shares a post.
     * </p>
     *
     * @param postId the ID of the post to be shared
     * @param userId the ID of the user sharing the post
     * @return a byte array representing the result of the operation
     */
    @Override
    public byte[] postShare(int postId, int userId) {
        return objectValidator.forSuccessResponse(postShareDAO.postShare(userId, postId), new byte[]{});
    }

    /**
     * <p>
     * Unshares a post.
     * </p>
     *
     * @param shareId the ID of the post share to be removed
     * @return a byte array representing the result of the operation
     */
    @Override
    public byte[] removeShare(int shareId) {
        return objectValidator.manualResponse(postShareDAO.removeShare(shareId));
    }
}
