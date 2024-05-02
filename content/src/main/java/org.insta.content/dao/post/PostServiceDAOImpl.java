package org.insta.content.dao.post;

import org.insta.content.dao.post.like.PostLikeDAOImpl;
import org.insta.content.model.common.IdSetter;
import org.insta.databaseconnection.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.model.post.Post;
import org.insta.content.model.home.Media;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Managing user post.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostServiceDAOImpl implements PostServiceDAO {

    private static PostServiceDAOImpl postServiceDAOImpl;
    private final Connection connection;
    private final PostLikeDAOImpl postLikeDAOImpl;
    private final IdSetter idSetter;
    private static final Logger LOGGER =  LogManager.getLogger(PostServiceDAOImpl.class);

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostServiceDAOImpl() {
        connection = DatabaseConnection.get();
        postLikeDAOImpl = PostLikeDAOImpl.getInstance();
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
     * Post a video or image for the user account
     * </p>
     *
     * @param post {@link Post} Refers the post for the user.
     * @return True if the post is added successfully, otherwise false.
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

        } catch (SQLException sqlException) {
            LOGGER.debug("Operation failed");
        }

        return 0;
    }

    /**
     * <p>
     * Deletes a post for the user account
     * </p>
     *
     * @param postId Refers the postId for the user.
     * @param userId Refers the userId for the user.
     * @return True if the post is updated successfully, otherwise false.
     */
    public boolean removePost(final int id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "DELETE FROM POST WHERE post.id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException exception) {
            LOGGER.debug("Operation failed");
        }
        return false;
    }

    /**
     * <p>
     * Update a post for the user account
     * </p>
     *
     * @param post {@link Post}  Refers the post for the user.
     * @return True if the post is updated successfully, otherwise false.
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
            LOGGER.debug("Update failed");
        }
        return false;
    }

    /**
     * <p>
     * Displays a post for the all user
     * </p>
     *
     * @param id Refers the userId of the user.
     * @return List if the post is fetched successfully, otherwise null.
     */
    public List<Post> displayPost(final int id) {
        List<Post> posts = null;

        try (final PreparedStatement preparedStatement = DatabaseConnection.get()
                .prepareStatement(getPostDisplayQuery())) {
            posts = new ArrayList<>();

            preparedStatement.setInt(1,id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return setPost(resultSet, posts);

        } catch (final SQLException exception) {
            LOGGER.debug("Operation failed ");
        }
        return posts;
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
        } catch (final SQLException exception) {
            LOGGER.debug("Operation failed");
        }
        return null;
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
                , " , post.caption, post.type, post.is_private, post.created_at, count(post_like.post_id),"
                , " count(post_comment.post_id), count(post_share.post_id) from post"
                , " left join account on account.id = post.user_id"
                , " left join post_like on post.id = post_like.post_id"
                , " left join post_comment on post.id = post_comment.post_id"
                , " left join post_share on post.id = post_share.post_id"
                , " where post.user_id = ? "
                , " group by post.id, account.id, "
                , " order by post.id;");
    }

    public Post getPost(final int postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(reelGetQuery())) {
            preparedStatement.setInt(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return setReelUnique(resultSet);
        } catch (Exception ignored) {
        }
        return null;
    }

    private Post setReelUnique( final ResultSet resultSet) {
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
        } catch (final SQLException exception) {
            LOGGER.debug("Operation failed");
        }
        return null;
    }

    private String reelGetQuery() {
        return String.join(" ", "select post.id, post.user_id, account.name,",
                " post.caption, post.type, post.is_private, post.created_at,(SELECT COUNT(*) FROM post_like WHERE post_like.post_id = post.id)",
                " AS like_count, (SELECT COUNT(*) FROM post_comment WHERE post_comment.post_id = post.id)",
                " AS comment_count, (SELECT COUNT(*) FROM post_share WHERE post_share.post_id = post.id) AS share_count",
                " from post left join account on account.id = post.user_id where post.id = ?;");
    }
}
