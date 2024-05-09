package org.insta.content.dao.reel;

import org.insta.content.dao.reel.query.QueryGenerator;
import org.insta.content.exception.reel.*;
import org.insta.content.model.common.IdSetter;
import org.insta.content.model.reel.Reel;
import org.insta.databaseconnection.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Managing user reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelServiceDAO
 * @see IdSetter
 * @see QueryGenerator
 * @see DatabaseConnection
 */
public final class ReelServiceDAOImpl implements ReelServiceDAO {

    private static ReelServiceDAO reelServiceDAOImpl;
    private final Connection connection;
    private final QueryGenerator queryGenerator;
    private final IdSetter idSetter;
    private final Logger LOGGER = LogManager.getLogger(ReelServiceDAOImpl.class);

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelServiceDAOImpl() {
        queryGenerator = QueryGenerator.getInstance();
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelServiceDAOImpl class.
     * </p>
     *
     * @return The singleton instance of ReelServiceDAOImpl class.
     */
    public static ReelServiceDAO getInstance() {
        return reelServiceDAOImpl == null ? reelServiceDAOImpl = new ReelServiceDAOImpl() : reelServiceDAOImpl;
    }

    /**
     * <p>
     * Adds a reel for the user account.
     * </p>
     *
     * @param reel The reel to be added.
     * @return The ID of the added reel if successful, otherwise 0.
     */
    @Override
    public int addReel(final Reel reel) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ",
                "INSERT INTO REELS  (user_id, caption, duration) VALUES (?, ?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reel.getUserId());
            preparedStatement.setString(2, reel.getCaption());
            preparedStatement.setString(3, reel.getDuration());

            if (preparedStatement.executeUpdate() > 0) {
                reel.setReelId(idSetter.setId(preparedStatement));
                return reel.getReelId();
            }

            return 0;
        } catch (SQLException ignored) {
            throw new ReelCreationFailedException("Reel creation failed");
        }
    }

    /**
     * <p>
     * Deletes a reel for the user account.
     * </p>
     *
     * @param reelId The ID of the reel to be deleted.
     * @return True if the reel is deleted successfully, otherwise false.
     */
    public boolean removeReel(final int reelId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ",
                "DELETE FROM REELS WHERE id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reelId);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException ignored) {
            throw new ReelRemovalFailedException("Reel removal failed");
        }
    }

    /**
     * <p>
     * Updates a reel for the user account.
     * </p>
     *
     * @param reel The updated reel.
     * @return True if the reel is updated successfully, otherwise false.
     */
    public boolean updateReel(final Reel reel) {
        final List<Object> list = new ArrayList<>();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "UPDATE reels", generateQuery(list, reel), "WHERE id = ? "))) {

            connection.setAutoCommit(true);
            for (int index = 1; index <= list.size(); index++) {
                preparedStatement.setObject(index, list.get(index - 1));
            }

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new ReelUpdateFailedException("Reel update failed");
        }
    }

    /**
     * <p>
     * Generate the query for the update feature.
     * </p>
     *
     * @param values Refers the values of the updated date.
     * @param reel   Refers the reel of the user
     * @return List if the fetched details is added successfully, otherwise null.
     */
    public String generateQuery(final List<Object> values, final Reel reel) {
        final List<String> queryList = new ArrayList<>();

        queryGenerator.getQuery(reel, queryList, values);

        return String.join(",", queryList);
    }

    /**
     * <p>
     * Retrieves all reels associated with a user.
     * </p>
     *
     * @param userId The ID of the user whose reels are to be retrieved.
     * @return A list of reels associated with the user, or an empty list if none are found.
     */
    public List<Reel> displayReel(final int userId) {
        List<Reel> reels = null;

        try (final PreparedStatement preparedStatement = connection.prepareStatement(getReelDisplayQuery())) {
            reels = new ArrayList<>();

            preparedStatement.setInt(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return setReel(reels, resultSet);

        } catch (final SQLException exception) {
            throw new ReelRetrivalFailedException("Reel retrival failed");
        }
    }

    /**
     * <p>
     * Sets the fetched details in the list.
     * </p>
     *
     * @param resultSet {@link ResultSet} Refers the resultSet which is fetched.
     * @return List if the fetched details is added successfully, otherwise null.
     */
    public List<Reel> setReel(final List<Reel> reels, final ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Reel reel = new Reel();

                reel.setReelId(resultSet.getInt(1));
                reel.setUserId(resultSet.getInt(2));
                reel.setUserName(resultSet.getString(3));
                reel.setCaption(resultSet.getString(4));
                reel.setPrivate(resultSet.getBoolean(5));
                reel.setTimestamp(resultSet.getTimestamp(6));
                reel.setDuration(resultSet.getString(7));
                reel.setTotal_likes(resultSet.getInt(8));
                reel.setTotal_comment(resultSet.getInt(9));
                reel.setTotal_shares(resultSet.getInt(10));
                reels.add(reel);
            }
            return reels;
        } catch (final SQLException exception) {
            throw new ReelRetrivalFailedException("Reel retrival failed");
        }
    }

    /**
     * <p>
     * Gets the post display query.
     * </p>
     *
     * @return Post display query.
     */
    private String getReelDisplayQuery() {
        return String.join(" ", "select reels.id, reels.user_id, account.name,"
                , " reels.caption, reels.is_private, reels.created_at,"
                , " reels.duration, (SELECT COUNT(*) FROM reel_like WHERE reel_like.reel_id = reels.id)"
                , " AS like_count, (SELECT COUNT(*) FROM reel_comment WHERE reel_comment.reel_id = reels.id)"
                , " AS comment_count, (SELECT COUNT(*) FROM reel_share WHERE reel_share.reel_id = reels.id) AS share_count"
                , " from reels left join account on account.id = reels.user_id);");
    }

    /**
     * <p>
     * Sets the reel ID based on the generated keys in the PreparedStatement.
     * </p>
     *
     * @param preparedStatement The PreparedStatement containing the generated keys.
     * @param reel              {@link Reel} belong to a user.
     * @return True if the user ID is successfully set, otherwise false.
     */
    public boolean setReelId(final PreparedStatement preparedStatement, final Reel reel) {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet != null && resultSet.next()) {
                reel.setReelId(resultSet.getInt("id"));

                return true;
            } else {
                return false;
            }
        } catch (SQLException ignored) {
            throw new ReelException("Resultset insertion in local object failed exception");
        }
    }

    /**
     * <p>
     * Retrieves a reel based on its ID.
     * </p>
     *
     * @param reelId The ID of the reel to be retrieved.
     * @return The retrieved reel, or null if not found.
     */
    public Reel getReel(final int reelId) {
        final Reel reel = new Reel();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(reelGetQuery())) {
            preparedStatement.setInt(1, reelId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return setReelUnique(reel, resultSet);
        } catch (Exception ignored) {
            throw new ReelRetrivalFailedException("Reel retrival failed");
        }
    }

    /**
     * <p>
     * Sets unique properties of a reel based on the retrieved ResultSet.
     * </p>
     *
     * @param reel      The reel object to be populated with unique properties.
     * @param resultSet The ResultSet containing reel data.
     * @return The populated reel object if successful, otherwise null.
     */
    private Reel setReelUnique(final Reel reel, final ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                reel.setReelId(resultSet.getInt(1));
                reel.setUserId(resultSet.getInt(2));
                reel.setUserName(resultSet.getString(3));
                reel.setCaption(resultSet.getString(4));
                reel.setPrivate(resultSet.getBoolean(5));
                reel.setTimestamp(resultSet.getTimestamp(6));
                reel.setDuration(resultSet.getString(7));
                reel.setTotal_likes(resultSet.getInt(8));
                reel.setTotal_comment(resultSet.getInt(9));
                reel.setTotal_shares(resultSet.getInt(10));
            }

            return reel;
        } catch (Exception ignored) {
            throw new ReelException("Resultset insertion in object failed exception");
        }
    }

    /**
     * <p>
     * Generates the SQL query for retrieving a reel by its ID.
     * </p>
     *
     * @return The SQL query string for retrieving a reel by its ID.
     */
    public String reelGetQuery() {
        return String.join(" ", "select reels.id, reels.user_id, account.name,"
                , " reels.caption, reels.is_private, reels.created_at,"
                , " reels.duration, (SELECT COUNT(*) FROM reel_like WHERE reel_like.reel_id = reels.id)"
                , " AS like_count, (SELECT COUNT(*) FROM reel_comment WHERE reel_comment.reel_id = reels.id)"
                , " AS comment_count, (SELECT COUNT(*) FROM reel_share WHERE reel_share.reel_id = reels.id) AS share_count"
                , "from reels left join account on account.id = reels.user_id where reels.id = ?;");
    }
}
