package org.insta.content.dao.story.share;

import org.insta.content.dao.story.querybuilder.QueryBuilder;
import org.insta.content.exception.story.storyshare.StoryShareFailedException;
import org.insta.content.exception.story.storyshare.StoryShareRemovalFailedException;
import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user story share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryShareDAO
 */
public class StoryShareDAOImpl implements StoryShareDAO {

    private static StoryShareDAOImpl storyShareDAO;
    private final Connection connection;
    private final IdSetter idSetter;
    private final QueryBuilder queryBuilder;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private StoryShareDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        queryBuilder = QueryBuilder.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryShareDAOImpl class.
     * </p>
     *
     * @return The singleton instance of StoryShareDAOImpl class.
     */
    public static StoryShareDAOImpl getInstance() {
        return storyShareDAO == null ? new StoryShareDAOImpl() : storyShareDAO;
    }

    /**
     * <p>
     * Adds a share for the specified story.
     * </p>
     *
     * @param storyId  The ID of the story to be shared.
     * @param sharedBy The ID of the user who shared the story.
     * @return The ID of the added share if successful, otherwise 0.
     */
    @Override
    public int addShare(final int storyId, final int sharedBy) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                queryBuilder.getStoryShareQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, storyId);
            preparedStatement.setInt(2, sharedBy);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.setId(preparedStatement);
            }

            return 0;
        } catch (final SQLException ignored) {
            throw new StoryShareFailedException("Story share failed");
        }
    }

    /**
     * <p>
     * Removes a share for the specified story.
     * </p>
     *
     * @param id The ID of the story to be unshared.
     * @return True if the share is successfully removed, otherwise false.
     */
    @Override
    public boolean removeShare(final int id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                queryBuilder.getStoryUnshareQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException ignored) {
            throw new StoryShareRemovalFailedException("Story share remove failed");
        }
    }
}
