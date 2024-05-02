package org.insta.content.dao.hashtag;

import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public final class HashTagReels {

    private static HashTagReels hashTagReels;
    private final Connection connection;

    private HashTagReels() {
        connection = DatabaseConnection.get();
    }

    public static HashTagReels getInstance() {
        return hashTagReels == null ? hashTagReels = new HashTagReels() : hashTagReels;
    }

    public void insertHashtags(final List<String> hashtags, final int reelId) {

        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                String.join(" ", "insert into hashtag_reel(reel_id, hash_tag_id) values ",
                        " (?, (select id from hash_tag where hash_tag = ?))")
        )) {

            for (final String hashtag : hashtags) {
                preparedStatement.setInt(1, reelId);
                preparedStatement.setString(2, hashtag);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (final SQLException ignored) {
        }
    }
}
