package org.insta.content.controller.reel;

import org.insta.content.dao.reel.ReelServiceDAOImpl;
import org.insta.content.model.reel.Reel;

import java.util.List;


/**
 * <p>
 * Implementation of the ReelController class for managing reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelController {

    private static ReelController reelController;
    private final ReelServiceDAOImpl reelServiceDAOImpl;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelController() {
        reelServiceDAOImpl = ReelServiceDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelController class.
     * </p>
     *
     * @return The singleton instance of ReelController class.
     */
    public static ReelController getInstance() {
        return reelController == null ? reelController = new ReelController() : reelController;
    }

    /**
     * <p>
     * Adds a reel for the specified user.
     * </p>
     *
     * @param reel   Refer to the {@link Reel} of the user.
     * @param userId The userId of the user adding the post.
     * @return True if the reel is added successfully, otherwise false.
     */
    public int addReel(final Reel reel) {
        return reelServiceDAOImpl.addReel(reel);
    }

    /**
     * <p>
     * Removes a reel with the specified ID for the specified user.
     * </P>
     *
     * @param reelId Refer to reelId of the reel.
     * @param userId The userId of the user removing the reel.
     * @return True if the reel is removed successfully, otherwise false.
     */
    public boolean removeReel(final Integer reelId) {
        return false;
    }

    /**
     * <p>
     * Displays all reels of the users.
     * </P>
     *
     * @param userId Refer to userId of the user.
     * @return True if the reel is fetched successfully, otherwise false.
     */
    public List<Reel> display(final int userId) {
        return reelServiceDAOImpl.displayReel(userId);
    }

    public Reel get(final int reelId) {
        return reelServiceDAOImpl.getReel(reelId);
    }
}
