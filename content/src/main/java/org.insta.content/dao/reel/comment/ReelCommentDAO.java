package org.insta.content.dao.reel.comment;

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
 */
public final class ReelCommentDAO {

    private static ReelCommentDAO reelCommentDAO;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelCommentDAO() {
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
        return reelCommentDAO == null ?  new ReelCommentDAO() : reelCommentDAO;
    }

    /**
     * <p>
     * Add a comment for the particular reel.
     * </p>
     *
     * @param reelId  Refers the reelId for the reel.
     * @param userId  Refers the userId for the user.
     * @param caption Refers the caption of the reel.
     * @return True if the like is added successfully, otherwise false.
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

        } catch (final SQLException ignored) {
        }

        return comment.getUserId();
    }

    /**
     * <p>
     * Delete a comment for the particular reel.
     * </p>
     *
     * @param commentId Refers the commentId of the comment.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean deleteComment(final int commentId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "Delete from reel_comment where id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, commentId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }

        return false;
    }
}
