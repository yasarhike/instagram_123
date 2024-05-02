package org.insta.content.dao.hashtag;

import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public final class HashTagDAO {

    private static HashTagDAO hashTag;
    private final Connection connection;

    private HashTagDAO() {
        connection = DatabaseConnection.get();
    }

    public static HashTagDAO getInstance(){
        return hashTag == null ? hashTag = new HashTagDAO() : hashTag;
    }

    public void insertHashtags(final List <String> hashtags) {

        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO hash_tag (hash_tag) VALUES (?) ON CONFLICT DO NOTHING"
        )) {

            for (final String hashtag : hashtags) {
                preparedStatement.setString(1, hashtag);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (final SQLException ignored) {
        }
    }
}
