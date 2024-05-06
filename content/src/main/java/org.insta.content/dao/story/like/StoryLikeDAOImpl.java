package org.insta.content.dao.story.like;

import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user story like.
 * </p>
 *
 * @see StoryLikeDAO
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class StoryLikeDAOImpl implements StoryLikeDAO {

    private static StoryLikeDAOImpl storyLikeDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private StoryLikeDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryLikeDAO class.
     * </p>
     *
     * @return The singleton instance of StoryLikeDAO class.
     */
    public static StoryLikeDAOImpl getInstance() {
        return storyLikeDAOImpl == null ? storyLikeDAOImpl = new StoryLikeDAOImpl() : storyLikeDAOImpl;
    }

    /**
     * <p>
     * Adds a like for the specified story by the user.
     * </p>
     *
     * @param userId  The ID of the user who likes the story.
     * @param storyId The ID of the story to be liked.
     * @return The ID of the inserted like if successful, otherwise 0.
     */
    public int storyLike(final int userId, final int storyId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "INSERT INTO story_like (story_id, liked_by)", "VALUES (?, ?)")
                , Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, storyId);
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
     * Removes a like for the specified story.
     * </p>
     *
     * @param storyId The ID of the story like to be removed.
     * @return True if the like is removed successfully, otherwise false.
     */
    public boolean storyUnlike(final int storyId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join
                (" ", "Delete from story_like where id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, storyId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }

        return false;
    }
}
