package org.insta.content.dao.reel.share;

import org.insta.content.exception.post.postshare.PostUnshareFailedException;
import org.insta.content.exception.reel.reelshare.ReelShareFailedException;
import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user reel share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelShareDAOImpl implements ReelShareDAO {

    private static ReelShareDAOImpl reelShareDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelShareDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelShareDAO class.
     * </p>
     *
     * @return The singleton instance of ReelShareDAO class.
     */
    public static ReelShareDAOImpl getInstance() {
        return reelShareDAOImpl == null ? reelShareDAOImpl = new ReelShareDAOImpl() : reelShareDAOImpl;
    }

    /**
     * <p>
     * Adds a user who shared the reel.
     * </p>
     *
     * @param userId The ID of the user who shared the reel.
     * @param reelId The ID of the reel being shared.
     * @return The ID of the share record if the user is successfully added as a sharer, otherwise 0.
     */
    public int reelShare(final int userId, final int reelId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "INSERT INTO reel_share (reel_id, shared_by)"
                        , "VALUES (?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reelId);
            preparedStatement.setInt(2, userId);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.setId(preparedStatement);
            }

            return 0;
        } catch (final SQLException ignored) {
            throw new ReelShareFailedException("Reel share failed exception");
        }
    }

    /**
     * <p>
     * Removes a user who unshared the reel.
     * </p>
     *
     * @param id The ID of the share record to be removed.
     * @return True if the user is successfully removed as a sharer, otherwise false.
     */
    public boolean removeShare(final int id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "Delete from reel_share "
                        , "where id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException ignored) {
            throw new PostUnshareFailedException("Reel unshare failed exception");
        }
    }
}
