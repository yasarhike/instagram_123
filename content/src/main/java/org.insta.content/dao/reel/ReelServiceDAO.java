package org.insta.content.dao.reel;

import org.insta.content.model.reel.Reel;

/**
 * <p>
 * Managing user reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface ReelServiceDAO {

    /**
     * <p>
     * Add a reel for the user account
     * </p>
     *
     * @param reel {@link Reel} Refers the reel for the user.
     * @return True if the reel is added successfully, otherwise false.
     */
    int addReel(final Reel reel);

    /**
     * <p>
     * Update a reel for the user account
     * </p>
     *
     * @param reel {@link Reel}  Refers the reel for the user.
     * @return True if the reel is updated successfully, otherwise false.
     */
    boolean updateReel(final Reel reel);

    /**
     * <p>
     * Deletes a reel for the user account
     * </p>
     *
     * @param postId Refers the postId for the user.
     * @param userId Refers the userId for the user.
     * @return True if the reel is deleted successfully, otherwise false.
     */
    boolean removeReel(final int reelId);
}
