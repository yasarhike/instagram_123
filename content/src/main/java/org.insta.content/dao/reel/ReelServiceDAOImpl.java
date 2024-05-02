package org.insta.content.dao.reel;

import org.insta.content.dao.hashtag.HashTagDAO;
import org.insta.content.dao.hashtag.HashTagReels;
import org.insta.content.dao.reel.query.QueryGenerator;
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
 */
public final class ReelServiceDAOImpl implements ReelServiceDAO {

    private static ReelServiceDAOImpl reelServiceDAOImpl;
    private final Connection connection;
    private final QueryGenerator queryGenerator;
    private final HashTagDAO hashTag;
    private final HashTagReels hashTagReels;
    private final Logger LOGGER = LogManager.getLogger(ReelServiceDAOImpl.class);

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelServiceDAOImpl() {
        queryGenerator = QueryGenerator.getInstance();
        connection = DatabaseConnection.get();
        hashTag = HashTagDAO.getInstance();
        hashTagReels = HashTagReels.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelServiceDAOImpl class.
     * </p>
     *
     * @return The singleton instance of ReelServiceDAOImpl class.
     */
    public static ReelServiceDAOImpl getInstance() {
        return reelServiceDAOImpl == null ? reelServiceDAOImpl = new ReelServiceDAOImpl() : reelServiceDAOImpl;
    }

    /**
     * <p>
     * Add a reel for the user account
     * </p>
     *
     * @param reel {@link Reel} Refers the reel for the user.
     * @return True if the reel is added successfully, otherwise false.
     */
    @Override
    public int addReel(final Reel reel) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ",
                "INSERT INTO REELS  (user_id, caption, duration) VALUES (?, ?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, reel.getUserId());
            preparedStatement.setString(2, reel.getCaption());
            preparedStatement.setString(3, reel.getDuration());

            hashTag.insertHashtags(reel.getHashTags());

            if (preparedStatement.executeUpdate() > 0 && setReelId(preparedStatement, reel)) {
                hashTagReels.insertHashtags(reel.getHashTags(), reel.getReelId());
                connection.commit();

                return reel.getReelId();
            } else {
                throw new SQLException();
            }
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (final SQLException ignored) {
            }
        }
        return 0;
    }

    /**
     * <p>
     * Deletes a reel for the user account
     * </p>
     *
     * @param reelId Refers the reelId for the user.
     * @return True if the reel is deleted successfully, otherwise false.
     */
    public boolean removeReel(final int reelId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ",
                "DELETE FROM REELS WHERE id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reelId);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException sqlException) {
            LOGGER.debug("Operation failed");
        }

        return false;
    }

    /**
     * <p>
     * Update a reel for the user account
     * </p>
     *
     * @param reel {@link Reel}  Refers the reel for the user.
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
            LOGGER.debug("Update failed");
        }

        return false;
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
     * Displays reel for the all user
     * </p>
     *
     * @param userId Refers the userId of the user.
     * @return List if the reel is fetched successfully, otherwise null.
     */
    public List<Reel> displayReel(final int userId) {
        List<Reel> reels = null;

        try (final PreparedStatement preparedStatement = connection.prepareStatement(getReelDisplayQuery())) {
            reels = new ArrayList<>();

            preparedStatement.setInt(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return setReel(reels, resultSet);

        } catch (final SQLException exception) {
            LOGGER.debug("Operation failed ");
        }
        return reels;
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
            LOGGER.debug("Operation failed");
        }
        return reels;
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
                , "from reels left join account on account.id = reels.user_id);");
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
        }
        return false;
    }

    public Reel getReel(final int reelId) {
        final Reel reel = new Reel();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(reelGetQuery())) {
            preparedStatement.setInt(1, reelId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return setReelUnique(reel, resultSet);
        } catch (Exception ignored) {
        }
        return null;
    }

    private Reel setReelUnique(final Reel reel, final ResultSet resultSet) {
        try{
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
                return reel;
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    public String reelGetQuery() {
        return String.join(" ", "select reels.id, reels.user_id, account.name,"
                , " reels.caption, reels.is_private, reels.created_at,"
                , " reels.duration, (SELECT COUNT(*) FROM reel_like WHERE reel_like.reel_id = reels.id)"
                , " AS like_count, (SELECT COUNT(*) FROM reel_comment WHERE reel_comment.reel_id = reels.id)"
                , " AS comment_count, (SELECT COUNT(*) FROM reel_share WHERE reel_share.reel_id = reels.id) AS share_count"
                , "from reels left join account on account.id = reels.user_id where reels.id = ?;");
    }
}
