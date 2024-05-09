package org.insta.content.dao.post;

import org.insta.authentication.exception.ProfileCreationFailedException;
import org.insta.authentication.exception.ProfileUpdateFailedException;
import org.insta.authentication.model.User;
import org.insta.content.exception.post.PostException;
import org.insta.content.exception.post.PostRemovalFailedException;
import org.insta.content.exception.post.PostRetrivalFailedException;
import org.insta.content.exception.post.PostUpdateFailedException;
import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.model.post.Post;
import org.insta.content.model.home.Media;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Implementation of the PostServiceDAO interface for managing user posts.
 * </p>
 *
 * <p>
 * This class provides methods to add, remove, update, and retrieve posts for users.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostServiceDAO
 */
public final class PostServiceDAOImpl implements PostServiceDAO {

    private static final Logger LOGGER = LogManager.getLogger(PostServiceDAOImpl.class);
    private static PostServiceDAOImpl postServiceDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostServiceDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostServiceDAOImpl class.
     * </p>
     *
     * @return The singleton instance of PostServiceDAOImpl class.
     */
    public static PostServiceDAOImpl getInstance() {
        return postServiceDAOImpl == null ? postServiceDAOImpl = new PostServiceDAOImpl() : postServiceDAOImpl;
    }

    /**
     * <p>
     * Posts a video or image for the user account.
     * </p>
     *
     * @param post the post to be added
     * @return the ID of the added post, or 0 if unsuccessful
     */
    public int addPost(final Post post) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "INSERT INTO post (user_id, caption," +
                        " is_private, type) VALUES (?, ?, ?, ?)"), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, post.getUserId());
            preparedStatement.setString(2, post.getCaption());
            preparedStatement.setBoolean(3, post.isPrivate());
            preparedStatement.setInt(4, post.getType().getId());

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.setId(preparedStatement);
            }

            return 0;
        } catch (SQLException sqlException) {
            throw new ProfileCreationFailedException("Profile creation failed");
        }
    }

    /**
     * <p>
     * Deletes a post for the user account.
     * </p>
     *
     * @param id the ID of the post to be removed
     * @return true if the post is removed successfully, otherwise false
     */
    public boolean removePost(final int id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "DELETE FROM POST WHERE post.id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException exception) {
            throw new PostRemovalFailedException("Post removal failed");
        }
    }

    /**
     * <p>
     * Updates a post for the user account.
     * </p>
     *
     * @param post the updated post
     * @return true if the post is updated successfully, otherwise false
     */
    public boolean updatePost(final Post post) {
        try {

            connection.setAutoCommit(true);
            final StringBuilder query = new StringBuilder("UPDATE post");
            final List<Object> list = new ArrayList<>();

            if (post.getCaption() != null) {
                query.append("SET caption = ?");
                list.add(post.getPostId());
            } else {
                query.append("SET is_private = ?");
                list.add(post.isPrivate());
            }

            query.append("WHERE id = ?");

            final PreparedStatement preparedStatement = connection
                    .prepareStatement(query.toString());

            for (int index = 1; index <= list.size(); index++) {
                preparedStatement.setObject(index, list.get(index - 1));
            }
        } catch (final SQLException exception) {
            throw new PostUpdateFailedException("Post update failed");
        }
        return false;
    }

    /**
     * <p>
     * Displays posts for all users.
     * </p>
     *
     * @param id the ID of the user
     * @return a list of posts if fetched successfully, otherwise null
     */
    public List<Post> displayPost(final int id) {
        List<Post> posts = null;

        try (final PreparedStatement preparedStatement = DatabaseConnection.get()
                .prepareStatement(getPostDisplayQuery(), Statement.RETURN_GENERATED_KEYS)) {
            posts = new ArrayList<>();

            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return setPost(resultSet, posts);

        } catch (final SQLException exception) {
            throw new PostRetrivalFailedException("Profile retrival failed");
        }
    }

    /**
     * <p>
     * Sets the fetched details in the list
     * </p>
     *
     * @param resultSet {@link ResultSet} Refers the resultSet which is fetched.
     * @return List if the fetched details is added successfully, otherwise null.
     */
    public List<Post> setPost(final ResultSet resultSet, final List<Post> posts) {

        try {
            while (resultSet.next()) {
                Post post = new Post();

                post.setPostId(resultSet.getInt(1));
                post.setUserId(resultSet.getInt(2));
                post.setUserName(resultSet.getString(3));
                post.setCaption(resultSet.getString(4));
                post.setType(Media.getMedia(resultSet.getInt(5)));
                post.setPrivate(resultSet.getBoolean(6));
                post.setTimestamp(resultSet.getTimestamp(7));
                post.setLikeCount(resultSet.getInt(8));
                post.setCommentCount(resultSet.getInt(9));
                post.setShareCount(resultSet.getInt(10));
                posts.add(post);
            }
            return posts;
        } catch (final SQLException ignored) {
            throw new PostRetrivalFailedException("Post retrival failed");
        }
    }

    /**
     * <p>
     * Gets the post display query.
     * </p>
     *
     * @return Post display query.
     */
    public String getPostDisplayQuery() {
        return String.join(" ", "select post.id, post.user_id, account.name"
                , " , post.caption, post.type, post.is_private, post.created_at,"
                , "(SELECT COUNT(*) FROM post_like WHERE post_like.post_id = post.id), "
                , " (SELECT COUNT(*) FROM post_comment WHERE post_comment.post_id = post.id), "
                , "(SELECT COUNT(*) FROM post_share WHERE post_share.post_id = post.id) from post "
                , " left join account on account.id = post.user_id"
                , " where post.user_id = ? ");
    }

    /**
     * <p>
     * Retrieves a post with the specified ID.
     * </p>
     *
     * @param postId the ID of the post to be retrieved
     * @return the retrieved post, or null if not found
     */
    public Post getPost(final int postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(reelGetQuery())) {
            preparedStatement.setInt(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return setReelUnique(resultSet);
        } catch (Exception ignored) {
            throw new ProfileCreationFailedException("Profile retrival failed");
        }
    }

    /**
     * <p>
     * Sets the details of a post retrieved from the database ResultSet.
     * </p>
     *
     * @param resultSet The ResultSet containing post details
     * @return The Post object with retrieved details, or null if no data found
     */
    private Post setReelUnique(final ResultSet resultSet) {
        final Post post = new Post();
        try {
            if (resultSet.next()) {
                post.setPostId(resultSet.getInt(1));
                post.setUserId(resultSet.getInt(2));
                post.setUserName(resultSet.getString(3));
                post.setCaption(resultSet.getString(4));
                post.setType(Media.getMedia(resultSet.getInt(5)));
                post.setPrivate(resultSet.getBoolean(6));
                post.setTimestamp(resultSet.getTimestamp(7));
                post.setLikeCount(resultSet.getInt(8));
                post.setCommentCount(resultSet.getInt(9));
                post.setShareCount(resultSet.getInt(10));
                return post;
            }
        } catch (final SQLException ignored) {
            throw new PostException("Resultset insertion in object failed");
        }
        return null;
    }

    /**
     * <p>
     * Generates the SQL query to retrieve post details.
     * </p>
     *
     * @return The SQL query to fetch post details
     */
    private String reelGetQuery() {
        return String.join(" ", "select post.id, post.user_id, account.name,",
                " post.caption, post.type, post.is_private, post.created_at,(SELECT COUNT(*) FROM post_like WHERE post_like.post_id = post.id)",
                " AS like_count, (SELECT COUNT(*) FROM post_comment WHERE post_comment.post_id = post.id)",
                " AS comment_count, (SELECT COUNT(*) FROM post_share WHERE post_share.post_id = post.id) AS share_count",
                " from post left join account on account.id = post.user_id where post.user_id = ?;");
    }

    public boolean updateProfile(final Post receivedObject) {
        if (receivedObject.getPostId() <= 0) return false;

        final Post tableObject = getPost(receivedObject.getPostId());
        final Post updatedObject = createUpdatedObject(receivedObject, tableObject);

        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join("", "UPDATE post ",
                "SET caption = ?, is_private = ?",
                "WHERE id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setString(1, updatedObject.getCaption());
            preparedStatement.setBoolean(2, updatedObject.isPrivate());
            preparedStatement.setInt(3, updatedObject.getPostId());

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException sqlException) {
            throw new ProfileCreationFailedException("Profile creation failed");
        }
        return false;
    }

    /**
     * <p>
     * Updates a user profile.
     * </p>
     *
     * @param receivedObject The {@link Post} object containing the updated user data.
     * @param tableObject    Refers the current record in the storable.
     * @return Post if the user profile is successfully updated, otherwise false.
     */
    public Post createUpdatedObject(final Post receivedObject, final Post tableObject) {
        if (!Objects.isNull(receivedObject.isPrivate())) tableObject.setPrivate(receivedObject.isPrivate());
        if (!Objects.isNull(receivedObject.getCaption())) tableObject.setCaption(receivedObject.getCaption());

        return receivedObject;
    }
}
