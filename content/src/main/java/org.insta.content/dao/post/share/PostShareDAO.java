package org.insta.content.dao.post.share;

/**
 * <p>
 * Manage post share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface PostShareDAO {

    /**
     * <p>
     * share a post
     * </p>
     *
     * @param postId Refers the postId for the post.
     * @param userId Refers the userId for the user.
     * @return True if the like is added successfully, otherwise false.
     */
     int postShare(final int postId, final int userId);

    /**
     * <p>
     * unSHare a post
     * </p>
     *
     * @param shareId Refers the shareId for the post.
     * @return True if the like is added successfully, otherwise false.
     */
     boolean removeShare(final int shareId);
}
