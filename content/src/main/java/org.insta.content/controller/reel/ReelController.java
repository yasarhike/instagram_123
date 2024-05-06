package org.insta.content.controller.reel;

import org.insta.content.dao.reel.ReelServiceDAO;
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
 * @see ReelServiceDAO
 */
public final class ReelController {

    private static ReelController reelController;
    private final ReelServiceDAO reelServiceDAOImpl;

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
     * @param reel Refers to the {@link Reel} of the user.
     * @return id Refers the created id for the respective information.
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
     * @return True if the reel is removed successfully, otherwise false.
     */
    public boolean removeReel(final Integer reelId) {
        return false;
    }

    /**
     * <p>
     * Retrieves all reels of the users.
     * </P>
     *
     * @param userId Refer to userId of the user.
     * @return list of reels related to the userId.
     */
    public List<Reel> display(final int userId) {
        return reelServiceDAOImpl.displayReel(userId);
    }

    /**
     * <p>
     * Retrieves a specific reel of the users.
     * </P>
     *
     * @param reelId Refer to userId of the user.
     * @return reel related to the reelId.
     */
    public Reel get(final int reelId) {
        return reelServiceDAOImpl.getReel(reelId);
    }
}
