package org.insta.content.dao.post;

import org.insta.content.model.post.Post;

import java.util.List;

/**
 * <p>
 * Managing user post.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface PostServiceDAO {

    /**
     * <p>
     * Post a video or image for the user account
     * </p>
     *
     * @param post {@link Post} Refers the post for the user.
     * @return True if the post is added successfully, otherwise false.
     */
    int addPost(final Post post);

    /**
     * <p>
     * Delete a post for the user account
     * </p>
     *
     * @param postId Refers the postId for the user.
     * @param userId Refers the userId of the user.
     * @return True if the post is added successfully, otherwise false.
     */
    boolean removePost(final int postId);

    /**
     * <p>
     * Update a post for the user account
     * </p>
     *
     * @param post {@link Post}  Refers the post for the user.
     * @return True if the post is updated successfully, otherwise false.
     */
    public boolean updatePost(final Post post);

    /**
     * <p>
     * Displays a post for the all user
     * </p>
     *
     * @param id Refers the userId of the user.
     * @return List if the post is fetched successfully, otherwise null.
     */
    public List<Post> displayPost(final int id);

    public Post getPost(final int id);
}
