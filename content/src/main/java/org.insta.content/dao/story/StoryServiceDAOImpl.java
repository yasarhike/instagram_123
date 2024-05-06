package org.insta.content.dao.story;

import org.insta.content.model.common.IdSetter;
import org.insta.content.model.story.Story;
import org.insta.databaseconnection.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * managing story service operation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryServiceDAO
 */
public final class StoryServiceDAOImpl implements StoryServiceDAO {

    private static StoryServiceDAOImpl storyServiceDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;
    private final Logger logger = LogManager.getLogger(StoryServiceDAOImpl.class);

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private StoryServiceDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    public static StoryServiceDAOImpl getInstance() {
        return storyServiceDAOImpl == null ? storyServiceDAOImpl = new StoryServiceDAOImpl() : storyServiceDAOImpl;
    }

    /**
     * <p>
     * Adds a story for the specified user.
     * </p>
     *
     * @param story The story to be added.
     * @return The ID of the added story if successful, otherwise 0.
     */
    public int addStory(final Story story) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "INSERT INTO STORY  (user_id, caption ",
                        ", is_private, music, type) VALUES (?, ?, ?, ?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, story.getUserId());
            preparedStatement.setString(2, story.getText());
            preparedStatement.setBoolean(3, story.isPrivate());
            preparedStatement.setString(4, story.getMusic());
            preparedStatement.setInt(5, story.getMedia().getId());

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.setId(preparedStatement);
            }

        } catch (final SQLException ignored) {
        }
        return 0;
    }

    /**
     * <p>
     * Removes a story with the specified ID.
     * </p>
     *
     * @param id The ID of the story to be removed.
     * @return True if the story is successfully removed, otherwise false.
     */
    public boolean removeStory(final int id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "DELETE FROM STORY where id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException exception) {
            logger.debug("Operation failed");
        }
        return false;
    }

    /**
     * <p>
     * Retrieves a story with the specified ID.
     * </p>
     *
     * @param id The ID of the story to be retrieved.
     * @return The retrieved story, or null if not found.
     */
    public Story getStory(final int id) {
        final Story story = new Story();

        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(getUniqueStory())) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return setStoryUnique(story, preparedStatement.executeQuery());

        } catch (final SQLException exception) {
        }
        return null;
    }

    /**
     * <p>
     * Sets the unique details of a story from the given ResultSet.
     * </p>
     *
     * @param story     The story object to set the details to.
     * @param resultSet The ResultSet containing the story details.
     * @return The story object with the unique details set, or null if not found.
     */
    private Story setStoryUnique(final Story story, final ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                story.setStoryId(resultSet.getInt(1));
                story.setUserId(resultSet.getInt(2));
                story.setUserName(resultSet.getString(3));
                story.setText(resultSet.getString(4));
                story.setPrivate(resultSet.getBoolean(5));
                story.setMusic(resultSet.getString(6));
                story.setTimestamp(resultSet.getTimestamp(7));
                story.setTotalLikes(resultSet.getInt(8));
                story.setTotalShares(resultSet.getInt(9));
                return story;
            }
        } catch (final SQLException exception) {
        }
        return null;
    }

    /**
     * <p>
     * Display a story of the user.
     * </p>
     *
     * @param userId Refers the userId of the user.
     * @return List if story is fetched successfully, otherwise false.
     */
    public List<Story> displayStory(final int userId) {
        List<Story> reels = null;

        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(getStoryDisplayQuery())) {
            reels = new ArrayList<>();

            preparedStatement.setInt(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return setStory(resultSet, reels);

        } catch (final SQLException exception) {
            logger.debug("Operation failed ");
        }
        return reels;
    }

    /**
     * <p>
     * Sets the fetched details in the list
     * </p>
     *
     * @param resultSet {@link ResultSet} Refers the resultSet which is fetched.
     * @return List if the fetched details is added successfully, otherwise null.
     */
    private List<Story> setStory(final ResultSet resultSet, final List<Story> storys) {
        try {
            while (resultSet.next()) {
                Story story = new Story();

                story.setStoryId(resultSet.getInt(1));
                story.setUserId(resultSet.getInt(2));
                story.setUserName(resultSet.getString(3));
                story.setText(resultSet.getString(4));
                story.setPrivate(resultSet.getBoolean(5));
                story.setMusic(resultSet.getString(6));
                story.setTimestamp(resultSet.getTimestamp(7));
                story.setTotalLikes(resultSet.getInt(8));
                story.setTotalShares(resultSet.getInt(9));
                storys.add(story);
            }
            return storys;
        } catch (final SQLException exception) {
            logger.debug("Operation Failed");
        }
        return storys;
    }

    /**
     * <p>
     * Gets the story display query.
     * </p>
     *
     * @return story display query.
     */
    private String getStoryDisplayQuery() {
        return String.join(" ", "select story.id, story.user_id, account.name "
                , " ,  story.caption, story.is_private, story.music, story.created_at "
                , " , count(story_like.story_id), "
                , " count(story_share.story_id) from story"
                , " left join account on account.id = story.user_id "
                , " left join story_like on story.id = story_like.story_id "
                , " left join story_share on story.id = story_share.story_id "
                , " where story.user_id = ? "
                , " group by story.id, account.id "
                , " order by story.id;");
    }

    /**
     * <p>
     * Gets the SQL query to fetch a unique story.
     * </p>
     *
     * @return The SQL query for fetching a unique story.
     */
    private String getUniqueStory() {
        return String.join(" ", "select story.id, story.user_id, account.name"
                , ",  story.caption, story.is_private, story.music, story.created_at ,"
                , " (SELECT COUNT(*) FROM story_like WHERE story_like.story_id = story.id)"
                , " AS like_count, (SELECT COUNT(*) FROM story_share WHERE story_share.story_id = story.id) AS share_count"
                , " from story left join account on account.id = story.user_id where story.id = ?;");
    }
}
