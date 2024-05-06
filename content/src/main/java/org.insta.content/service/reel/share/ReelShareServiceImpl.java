package org.insta.content.service.reel.share;

import org.insta.content.dao.reel.share.ReelShareDAO;
import org.insta.content.dao.reel.share.ReelShareDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Service implementation for managing reel sharing operations.
 * </p>
 *
 * <p>
 * This class provides methods for adding and removing shares of reels, allowing users to share and unshare reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelShareService
 * @see ReelShareDAO
 * @see ObjectValidator
 */
public class ReelShareServiceImpl implements ReelShareService{

    private static ReelShareService reelShareService;
    private final ReelShareDAO reelShareDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Private constructor to restrict object creation outside of the class.
     * </p>
     */
    private ReelShareServiceImpl() {
        reelShareDAO = ReelShareDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     * Retrieves the singleton instance of ReelShareServiceImpl class.
     * </p>
     *
     * @return The singleton instance of ReelShareServiceImpl class.
     */
    public static ReelShareService getInstance() {
        return reelShareService == null ? new ReelShareServiceImpl() : reelShareService;
    }
    /**
     * <p>
     * Add the people who shared the reel.
     * </p>
     *
     * @param userId Refers to the id of the user.
     * @param reelId Refers to the reelId of the reel.
     * @return True if it is added successfully, otherwise false.
     */
    @Override
    public byte[] reelShare(int userId, int reelId) {
        return objectValidator.forSuccessResponse(reelShareDAO.reelShare(userId, reelId), new byte[]{});
    }

    /**
     * <p>
     * Remove the people who unshared the reel.
     * </p>
     *
     * @param id Refers to the id of the user.
     * @return True if it is unshared successfully, otherwise false.
     */
    @Override
    public byte[] removeShare(int id) {
        return objectValidator.manualResponse(reelShareDAO.removeShare(id));
    }
}
