package org.insta.content.dao.reel.share;

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
public final class ReelShareDAO {

    private static ReelShareDAO reelShareDAO;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelShareDAO() {
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
    public static ReelShareDAO getInstance() {
        return reelShareDAO == null ? reelShareDAO = new ReelShareDAO() : reelShareDAO;
    }

    /**
     * <p>
     * Add the people who shared the reel.
     * </p>
     *
     * @return True if it is added successfully, otherwise false.
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

        } catch (final SQLException ignored) {
        }
        return 0;
    }

    /**
     * <p>
     * Remove the people who unshared the reel.
     * </p>
     *
     * @return True if it is unshared successfully, otherwise false.
     */
    public boolean removeShare(final int id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "Delete from reel_share "
                        , "where id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }
        return false;
    }
}
