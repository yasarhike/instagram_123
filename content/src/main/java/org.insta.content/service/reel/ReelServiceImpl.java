package org.insta.content.service.reel;

import org.insta.content.model.reel.Reel;
import org.insta.content.service.home.content.ContentServiceImpl;

import java.util.Map;

/**
 * <p>
 * Implementation class for managing reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelServiceImpl {
    private static ReelServiceImpl reelServiceImpl;
    public final ContentServiceImpl<Reel> reelService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelServiceImpl() {
        reelService = new ContentServiceImpl<>();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelServiceImplementaion class.
     * </p>
     *
     * @return The singleton instance of ReelServiceImplementation class.
     */
    public static ReelServiceImpl getInstance() {
        if (reelServiceImpl == null) {
            reelServiceImpl = new ReelServiceImpl();
        }
        return reelServiceImpl;
    }

    /**
     * <p>
     * Adds a reel for the specified user and set the timestamp.
     * </p>
     *
     * @param reel   Refer to the {@link Reel} of the user.
     * @param userId Refer the userId of the user adding the post.
     * @return True if the reel is added successfully, otherwise false.
     */
    public boolean addReel(final Reel reel, final Integer userId) {
        reel.setReelId(ContentServiceImpl.getContentCount());
        return reelService.add(reel, userId);
    }

    /**
     * <p>
     * Removes a reel with the specified ID for the specified user.
     * </P>
     *
     * @param reelId Refer to reelId of the reel.
     * @param userId Refer the userId of the user removing the reel.
     * @return True if the reel is removed successfully, otherwise false.
     */
    public boolean removeReel(final int reelId, final Integer userId) {
        return reelService.remove(reelId, userId);
    }

    /**
     * <p>
     * Retrieves all reels.
     * </p>
     *
     * @return Map contains the user reels.
     */
    public Map<Integer, Map<Integer, Reel>> getReel() {
        return reelService.getContent();
    }
}
