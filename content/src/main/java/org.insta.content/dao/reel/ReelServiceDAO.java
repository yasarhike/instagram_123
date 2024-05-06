package org.insta.content.dao.reel;

import org.insta.content.model.reel.Reel;

import java.util.List;

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
     * Adds a reel for the user account.
     * </p>
     *
     * @param reel The reel to be added.
     * @return The ID of the added reel if successful, otherwise 0.
     */
    int addReel(final Reel reel);

    /**
     * <p>
     * Updates a reel for the user account.
     * </p>
     *
     * @param reel The updated reel.
     * @return True if the reel is updated successfully, otherwise false.
     */
    boolean updateReel(final Reel reel);

    /**
     * <p>
     * Deletes a reel for the user account.
     * </p>
     *
     * @param reelId The ID of the reel to be deleted.
     * @return True if the reel is deleted successfully, otherwise false.
     */
    boolean removeReel(final int reelId);

    /**
     * <p>
     * Retrieves a reel based on its ID.
     * </p>
     *
     * @param reelId The ID of the reel to be retrieved.
     * @return The retrieved reel, or null if not found.
     */
    Reel getReel(final int reelId);

    /**
     * <p>
     * Retrieves all reels associated with a user.
     * </p>
     *
     * @param userId The ID of the user whose reels are to be retrieved.
     * @return A list of reels associated with the user, or an empty list if none are found.
     */
    List<Reel> displayReel(final int userId);
}
