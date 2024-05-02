package org.insta.content.dao.reel.reelfeed;

import org.insta.databaseconnection.DatabaseConnection;
import org.insta.content.model.reel.Reel;
import org.insta.content.dao.reel.ReelServiceDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Managing user reel feed.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelFeedServiceDAO {

    private static ReelFeedServiceDAO reelFeedService;
    private final Connection connection;
    private final ReelServiceDAOImpl reelServiceDAOImpl;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelFeedServiceDAO() {
        connection = DatabaseConnection.get();
        reelServiceDAOImpl = ReelServiceDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelFeedServiceDAO class.
     * </p>
     *
     * @return The singleton instance of REelFeedServiceDAO class.
     */
    public static ReelFeedServiceDAO getInstance() {
        return reelFeedService == null ? reelFeedService = new ReelFeedServiceDAO() : reelFeedService;
    }

    /**
     * <p>
     * Gets the reels of all user
     * </p>
     *
     * @return List if it is fetched successfully, otherwise null.
     */
    public List<Reel> getReels() {
        List<Reel> reels = null;

        try (final PreparedStatement preparedStatement = Objects.requireNonNull(DatabaseConnection.get())
                .prepareStatement(getReelDisplayQuery())) {
            reels = new ArrayList<>();

            final ResultSet resultSet = preparedStatement.executeQuery();
            return setReel(reels, resultSet);

        } catch (final SQLException exception) {
        }
        return reels;
    }

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
        } catch (final SQLException ignored) {
        }
        return reels;
    }

    private String getReelDisplayQuery() {
        return String.join(" ", "select reels.id, reels.user_id, account.name,  reels.caption,"
                , " reels.is_private, reels.created_at, reels.duration,"
                , " (SELECT COUNT(*) FROM reel_like WHERE reel_like.reel_id = reels.id) AS like_count, "
                , " (SELECT COUNT(*) FROM reel_comment WHERE reel_comment.reel_id = reels.id) AS comment_count, "
                , " (SELECT COUNT(*) FROM reel_share WHERE reel_share.reel_id = reels.id) AS share_count from reels"
                , " left join account on account.id = reels.user_id"
                , " group by reels.id, account.id"
                , " order by reels.id"
        );
    }
}
