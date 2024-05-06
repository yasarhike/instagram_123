package org.insta.content.dao.post.like;

import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user post like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostLikeDAOImpl implements PostLikeDAO{

    private static PostLikeDAOImpl postLikeDAOImpl;
    private final IdSetter idSetter;
    private final Connection connection;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostLikeDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeDAO class.
     * </p>
     *
     * @return The singleton instance of POstLikeDAO class.
     */
    public static PostLikeDAOImpl getInstance() {
        return postLikeDAOImpl == null ? postLikeDAOImpl = new PostLikeDAOImpl() : postLikeDAOImpl;
    }

    /**
     * <p>
     * Add a like for the particular post
     * </p>
     *
     * @param postId Refers the postId for the user.
     * @return True if the like is added successfully, otherwise false.
     */
    public int postLike(final int userId, final int postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "INSERT INTO post_like (post_id, liked_by)", "VALUES (?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

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
     * @param postId Refers the postId for the post.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean postUnlike(final int postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "Delete from post_like ", "where id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, postId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException ignored) {
        }

        return false;
    }
}
