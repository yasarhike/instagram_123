package org.insta.content.dao.reel.comment;

import org.insta.content.exception.reel.reelcomment.ReelCommentFailedException;
import org.insta.content.exception.reel.reelcomment.ReelCommentRemovalFailedException;
import org.insta.content.model.Comment;
import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user reel comment.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelCommentDAO
 */
public final class ReelCommentDAOImpl implements ReelCommentDAO {

    private static ReelCommentDAOImpl reelCommentDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelCommentDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelCommentDAO class.
     * </p>
     *
     * @return The singleton instance of ReelCommentDAO class.
     */
    public static ReelCommentDAO getInstance() {
        return reelCommentDAOImpl == null ? new ReelCommentDAOImpl() : reelCommentDAOImpl;
    }

    /**
     * <p>
     * Adds a comment for a particular reel.
     * </p>
     *
     * @param comment The comment to be added
     * @return The ID of the added comment, or 0 if unsuccessful
     */
    public int addComment(final Comment comment) {
        comment.setId(0);
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ",
                "INSERT INTO reel_comment (reel_id, commented_by, content ) VALUES (?, ?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, comment.getContentId());
            preparedStatement.setInt(2, comment.getUserId());
            preparedStatement.setString(3, comment.getComment());

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.setId(preparedStatement, comment);
            }

            return comment.getUserId();
        } catch (final SQLException ignored) {
            throw new ReelCommentFailedException("Reel comment failed");
        }
    }

    /**
     * <p>
     * Deletes a comment for a particular reel.
     * </p>
     *
     * @param commentId The ID of the comment to be deleted
     * @return true if the comment is deleted successfully, otherwise false
     */
    public boolean deleteComment(final int commentId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "Delete from reel_comment where id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, commentId);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException ignored) {
            throw new ReelCommentRemovalFailedException("Reel comment removal failed");
        }
    }
}
