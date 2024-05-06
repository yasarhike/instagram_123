package org.insta.content.service.story.share;

import org.insta.content.dao.story.share.StoryShareDAO;
import org.insta.content.dao.story.share.StoryShareDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Implementation class for managing story sharing operations.
 * </p>
 *
 * <p>
 * This class provides methods to share and unshare stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryShareService
 * @see StoryShareDAO
 * @see ObjectValidator
 */
public class StoryShareServiceImpl implements StoryShareService {

    private static StoryShareService storyShareService;
    private final StoryShareDAO storyShareDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryShareServiceImpl() {
        storyShareDAO = StoryShareDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryShareServiceImpl class.
     * </p>
     *
     * @return The singleton instance of StoryShareServiceImpl class.
     */
    public static StoryShareService getInstance() {
        return storyShareService == null ? new StoryShareServiceImpl() : storyShareService;
    }

    /**
     * <p>
     * Shares a story with the specified story ID and user ID.
     * </p>
     *
     * @param storyId The ID of the story to share.
     * @param userId  The ID of the user sharing the story.
     * @return A byte array representing a success response.
     */
    @Override
    public byte[] storyShare(int storyId, int userId) {
        return objectValidator.forSuccessResponse(storyShareDAO.addShare(storyId, userId), new byte[]{});
    }

    /**
     * <p>
     * Unshares a story with the specified story ID.
     * </p>
     *
     * @param storyId The ID of the story to unshare.
     * @return A byte array representing a manual response.
     */
    @Override
    public byte[] storyUnShare(int storyId) {
        return objectValidator.manualResponse(storyShareDAO.removeShare(storyId));
    }
}
