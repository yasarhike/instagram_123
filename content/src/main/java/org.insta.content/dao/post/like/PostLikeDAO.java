package org.insta.content.dao.post.like;

/**
 * <p>
 * Manage post like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface PostLikeDAO {

    /**
     * <p>
     * Add a like for a post.
     * </p>
     *
     * @param postId Refers the postId for the user.
     * @return True if the like is added successfully, otherwise false.
     */
     int postLike(final int userId, final int postId);

    /**
     * <p>
     * Unlike a particular post
     * </p>
     *
     * @param postId Refers the postId for the post.
     * @param userId Refers the userId for the user.
     * @return True if the like is added successfully, otherwise false.
     */
     boolean postUnlike(final int userId, final int postId);
}
