package org.insta.content.dao.post.comment;

import org.insta.content.model.Comment;

/**
 * <p>
 * Manage post comments.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface PostCommentDAO {

    /**
     * <p>
     * Add a comment for the post.
     * </p>
     *
     * @param comment {@link Comment}  Refers the id of the user.
     * @return True if the comment is added successfully, otherwise false.
     */
    int postComment(final Comment comment);

    /**
     * <p>
     * Delete a comment for the post.
     * </p>
     *
     * @param id Refers the commentId for the post.
     * @return True if the comment is deleted successfully, otherwise false.
     */
    boolean deleteComment(final int id);
}
