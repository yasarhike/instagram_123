package org.insta.content.dao.reel.like;

import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user reel like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelLikeDAO
 */
public final class ReelLikeDAOImpl implements ReelLikeDAO {

    private static ReelLikeDAOImpl reelLikeDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelLikeDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelLikeDAO class.
     * </p>
     *
     * @return The singleton instance of ReelLikeDAO class.
     */
    public static ReelLikeDAOImpl getInstance() {
        return reelLikeDAOImpl == null ? reelLikeDAOImpl = new ReelLikeDAOImpl() : reelLikeDAOImpl;
    }

    /**
     * <p>
     * Adds a like for a particular reel.
     * </p>
     *
     * @param reelId The ID of the reel to like
     * @param userId The ID of the user performing the like
     * @return The ID of the added like, or 0 if unsuccessful
     */
    public int reelLike(final int reelId, final int userId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "INSERT INTO reel_like (reel_id, liked_by) VALUES (?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reelId);
            preparedStatement.setInt(2, userId);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.setId(preparedStatement);
            }
        } catch (final SQLException ignored) {
        }
        return 0;
    }

    /**
     * <p>
     * Unlikes a particular reel.
     * </p>
     *
     * @param id The ID of the reel to unlike
     * @return true if the reel is unliked successfully, otherwise false
     */
    public boolean reelUnlike(final int id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "Delete from reel_like where id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }

        return false;
    }
}
