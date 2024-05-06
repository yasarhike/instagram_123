package org.insta.content.dao.post.share;

import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Data Access Object interface for managing post shares.
 * </p>
 *
 * <p>
 * This interface provides methods for sharing and unsharing posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostShareDAO
 */
public final class PostShareDAOImpl implements PostShareDAO {

    private static PostShareDAOImpl postShareDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostShareDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostShareDAOImpl class.
     * </p>
     *
     * @return The singleton instance of PostShareDAOImpl class.
     */
    public static PostShareDAOImpl getInstance() {
        return postShareDAOImpl == null ? new PostShareDAOImpl() : postShareDAOImpl;
    }

    /**
     * <p>
     * Shares a post.
     * </p>
     *
     * @param postId the ID of the post to be shared
     * @param userId the ID of the user sharing the post
     * @return the ID of the added share, or 0 if unsuccessful
     */
    public int postShare(final int postId, final int userId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "INSERT INTO post_share (post_id, shared_by)", "VALUES (?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.setId(preparedStatement);
            }
        } catch (SQLException ignored) {
        }
        return 0;
    }

    /**
     * <p>
     * Unshares a post.
     * </p>
     *
     * @param shareId the ID of the share to be removed
     * @return true if the share is removed successfully, otherwise false
     */
    public boolean removeShare(final int shareId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "Delete from post_share ", "where  id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, shareId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException ignored) {
        }

        return false;
    }
}
