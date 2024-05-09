package org.insta.content.dao.post.comment;

import org.insta.content.exception.post.postcomment.PostCommentFailedException;
import org.insta.content.exception.post.postcomment.PostUncommentFailedException;
import org.insta.content.model.Comment;
import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.*;

/**
 * <p>
 * Managing post comments.
 * </p>
 *
 * <p>
 * This interface provides methods for adding and deleting comments for a post.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostCommentDAO
 */
public final class PostCommentDAOImpl implements PostCommentDAO {

    private static PostCommentDAOImpl postCommentDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentDAO class.
     * </p>
     *
     * @return The singleton instance of PstCommentDAO class.
     */
    public static PostCommentDAOImpl getInstance() {
        return postCommentDAOImpl == null ? postCommentDAOImpl = new PostCommentDAOImpl() : postCommentDAOImpl;
    }

    /**
     * <p>
     * Adds a comment for the post.
     * </p>
     *
     * @param comment the comment to be added
     * @return the ID of the added comment, or 0 if unsuccessful
     */
    public int postComment(final Comment comment) {
        comment.setId(0);

        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "INSERT INTO post_comment (post_id, commented_by, content )", "VALUES (?, ?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, comment.getContentId());
            preparedStatement.setInt(2, comment.getUserId());
            preparedStatement.setString(3, comment.getComment());
            preparedStatement.executeUpdate();

            return idSetter.setId(preparedStatement, comment);
        } catch (final SQLException ignored) {
            throw new PostCommentFailedException("Post comment failed");
        }
    }

    /**
     * <p>
     * Delete a comment for the post
     * </p>
     *
     * @param id Refers the commentId for the post.
     * @return True if the comment is deleted successfully, otherwise false.
     */
    public boolean deleteComment(final int id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "Delete from post_comment where id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException ignored) {
            throw new PostUncommentFailedException("Post comment removal failed");
        }
    }
}
