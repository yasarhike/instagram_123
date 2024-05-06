package org.insta.content.controller.post.comment;

import org.insta.content.dao.post.comment.PostCommentDAOImpl;
import org.insta.content.model.Comment;

/**
 * <p>
 * Implemented class for managing post comment operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostCommentDAOImpl
 */
public final class PostCommentController {

    private static PostCommentController postCommentController;
    private final PostCommentDAOImpl postCommentDAOImpl;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentController() {
        postCommentDAOImpl = PostCommentDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentController class.
     * </p>
     *
     * @return The singleton instance of PostCommentController class.
     */
    public static PostCommentController getInstance() {
        return postCommentController == null ? postCommentController = new PostCommentController() : postCommentController;
    }

    /**
     * <p>
     * Adds a comment for the specified post.
     * </p>
     *
     * @param comment the comment object to be added
     * @return the ID of the added comment if successful, otherwise -1
     */
    public int addComment(final Comment comment) {
        return postCommentDAOImpl.postComment(comment);
    }

    /**
     * <p>
     * Removes a comment for the specified post.
     * </p>
     *
     * @param id the ID of the comment to be removed
     * @return true if the comment is removed successfully, otherwise false
     */
    public boolean removeComment(final int id) {
        return postCommentDAOImpl.deleteComment(id);
    }
}
