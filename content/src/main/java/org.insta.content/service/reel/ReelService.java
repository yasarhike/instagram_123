package org.insta.content.service.reel;

import org.insta.content.model.reel.Reel;

/**
 * <p>
 * Managing reels service operation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface ReelService {

    /**
     * <p>
     * Adds a reel for the specified user and set the timestamp.
     * </p>
     *
     * @param reel   Refer to the {@link Reel} of the user.
     * @param userId Refer the userId of the user adding the post.
     * @return True if the reel is added successfully, otherwise false.
     */
    boolean addReel(final Reel reel, final Integer userId);

    /**
     * <p>
     * Removes a reel with the specified ID for the specified user.
     * </P>
     *
     * @param reelId Refer to reelId of the reel.
     * @param userId Refer the userId of the user removing the reel.
     * @return True if the reel is removed successfully, otherwise false.
     */
    boolean removeReel(final int reelId, final Integer userId);
}
