package org.insta.content.dao.post.share;

import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Manage post share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostShareDAOImpl implements PostShareDAO{

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
     * Like a particular post
     * </p>
     *
     * @param postId Refers the postId for the post.
     * @param userId Refers the userId for the user.
     * @return True if the like is added successfully, otherwise false.
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
     * Unlike a particular post
     * </p>
     *
     * @param shareId Refers the shareId for the post.
     * @return True if the like is added successfully, otherwise false.
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
