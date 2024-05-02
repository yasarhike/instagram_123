package org.insta.content.service.post;

import org.insta.content.model.post.Post;

/**
 * <p>
 * Managing post service operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface PostService {

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post   Refer to the post to the user.
     * @return True if the post is added successfully, otherwise false.
     */
     byte[] addPost(final Post post);

    /**
     * <p>
     * Removes a post with the specified ID for the specified user.
     * </P>
     *
     * @param postId Refer to id of the post.
     * @return True if the post is removed successfully, otherwise false.
     */
     byte[] removePost(final int postId);

    /**
     * <p>
     * get a post with the specified ID for the specified user.
     * </P>
     *
     * @param id Refer to id of the post.
     * @return True if the post is removed successfully, otherwise false.
     */
     byte[] getPost(final int id);

    /**
     * <p>
     * Update a post with the specified ID for the specified user.
     * </P>
     *
     * @param post {@link Post} Refer to post of the user want to update.
     * @return True if the post is removed successfully, otherwise false.
     */
     byte[] updatePost(final Post post);
}
