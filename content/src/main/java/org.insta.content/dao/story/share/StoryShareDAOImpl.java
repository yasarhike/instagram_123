package org.insta.content.dao.story.share;

import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StoryShareDAOImpl implements StoryShareDAO{

    private static StoryShareDAOImpl storyShareDAO;
    private final Connection connection;
    private final IdSetter idSetter;

    private StoryShareDAOImpl() {
       connection = DatabaseConnection.get();
       idSetter = IdSetter.getInstance();
    }

    public static StoryShareDAOImpl getInstance() {
        return storyShareDAO == null ? new StoryShareDAOImpl() : storyShareDAO;
    }

    @Override
    public int addShare(final int storyId, final int sharedBy) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ",
                "INSERT INTO story_share (story_id, shared_by)", "VALUES (?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, storyId);
            preparedStatement.setInt(2, sharedBy);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.setId(preparedStatement);
            }

        } catch (final SQLException ignored) {
        }
        return 0;
    }

    @Override
    public boolean removeShare(final int id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ",
                "DELETE FROM story_share where id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }
        return false;
    }
}
