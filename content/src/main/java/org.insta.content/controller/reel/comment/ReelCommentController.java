package org.insta.content.controller.reel.comment;

import org.insta.content.dao.reel.comment.ReelCommentDAO;
import org.insta.content.model.Comment;

/**
 * <p>
 * Controller class responsible for managing comments on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelCommentController {

    private static ReelCommentController commentController;
    private final ReelCommentDAO reelCommentDAO;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelCommentController() {
        reelCommentDAO = ReelCommentDAO.getInstance();
    }

    /**
     * <p>
     * Creates a singleton instance of the ReelCommentController class if it is not already created.
     * </p>
     *
     * @return Singleton instance of ReelCommentController class.
     */
    public static ReelCommentController getInstance() {
        return commentController == null ? commentController = new ReelCommentController() : commentController;
    }

    /**
     * <p>
     * Adds a comment to the specified post.
     * </p>
     *
     * @param reelId  Refers the reelId of the reel to which the comment will be added.
     * @param comment Refers the comment to be added.
     * @return True if the comment is successfully added, false otherwise.
     */
    public int add(final Comment comment) {
        return reelCommentDAO.addComment(comment);
    }

    /**
     * <p>
     * Removes comments associated with the specified post.
     * </p>
     *
     * @param reelId Refers the reelId of the reel from which comments will be removed.
     * @return True if comments are successfully removed, otherwise false.
     */
    public boolean deleteComment(final int commentId) {
        return reelCommentDAO.deleteComment(commentId);
    }
}
