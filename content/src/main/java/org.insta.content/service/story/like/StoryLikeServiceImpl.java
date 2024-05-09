package org.insta.content.service.story.like;

import org.insta.content.dao.story.like.StoryLikeDAO;
import org.insta.content.dao.story.like.StoryLikeDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Implementation class for managing story liking operations.
 * </p>
 *
 * <p>
 * This class provides methods to like and unlike stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryLikeService
 * @see StoryLikeDAO
 * @see ObjectValidator
 */
public final class StoryLikeServiceImpl implements StoryLikeService {

    private static StoryLikeServiceImpl storyLikeService;
    private final StoryLikeDAO storyLikeDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryLikeServiceImpl() {
        storyLikeDAO = StoryLikeDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     * Creates a singleton instance of StoryLikeServiceImpl class if it is not already created.
     * </p>
     *
     * @return the singleton instance of StoryLikeServiceImpl class.
     */
    public static StoryLikeServiceImpl getInstance() {
        return storyLikeService == null ? new StoryLikeServiceImpl() : storyLikeService;
    }


    /**
     * <p>
     * Adds a like for the particular story.
     * </p>
     *
     * @param userId  The ID of the user liking the story.
     * @param storyId The ID of the story to like.
     * @return A byte array representing a success response.
     */
    @Override
    public byte[] storyLike(int userId, int storyId) {
        return objectValidator.forSuccessResponse(storyLikeDAO.storyLike(userId, storyId), new byte[]{});
    }

    /**
     * <p>
     * Removes a like for the particular story.
     * </p>
     *
     * @param id The ID of the story to unlike.
     * @return A byte array representing a manual response.
     */
    @Override
    public byte[] storyUnlike(int id) {
        return objectValidator.manualResponse(storyLikeDAO.storyUnlike(id));
    }
}
