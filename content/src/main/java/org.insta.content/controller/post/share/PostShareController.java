package org.insta.content.controller.post.share;

import org.insta.content.dao.post.share.PostShareDAOImpl;

/**
 * <p>
 * Manages post share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostShareDAOImpl
 */
public class PostShareController {

    private static PostShareController postShareController;
    private final PostShareDAOImpl postShareDAOImpl;

    private PostShareController() {
        postShareDAOImpl = PostShareDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostShareController class.
     * </p>
     *
     * @return The singleton instance of PostShareController class.
     */
    public static PostShareController getInstance() {
        return postShareController == null ? new PostShareController() : postShareController;
    }


    /**
     * <p>
     * Shares the specified post by the specified user.
     * </p>
     *
     * @param userId The ID of the user who shares the post.
     * @param postId The ID of the post to be shared.
     * @return The ID of the shared post.
     */
    public int postShare(final int userId, final int postId) {
        return postShareDAOImpl.postShare(userId, postId);
    }

    /**
     * <p>
     * Removes the share with the specified ID.
     * </p>
     *
     * @param shareId The ID of the share to be removed.
     * @return True if the share is removed successfully, otherwise false.
     */
    public boolean removeShare(final int shareId) {
        return postShareDAOImpl.removeShare(shareId);
    }
}