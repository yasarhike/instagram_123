package org.insta.content.controller.story.like;

import org.insta.content.dao.story.like.StoryLikeDAOImpl;

/**
 * <p>
 * Implemented class for managing story like operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class StoryLikeController {

    private static StoryLikeController storyLikeController;
    private final StoryLikeDAOImpl storyLikeDAOImpl;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryLikeController() {
        storyLikeDAOImpl = StoryLikeDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryLikeController class.
     * </p>
     *
     * @return The singleton instance of StoryLikeController class.
     */
    public static StoryLikeController getInstance() {
        return storyLikeController == null ? storyLikeController = new StoryLikeController() : storyLikeController;
    }

    /**
     * <p>
     * Adds a like for the specific story.
     * </P>
     *
     * @param userId  Refer the id of the user.
     * @param storyId Refer the id of the story.
     * @return True if the like is updated successfully, otherwise false.
     */
    public int storyLike(final int userId, final int storyId) {
        return storyLikeDAOImpl.storyLike(userId, storyId);
    }

    /**
     * <p>
     * Updates a post with the specified ID.
     * </P>
     *
     * @param storyId Refer the id of the story.
     * @return True if the like is removed successfully, otherwise false.
     */
    public boolean storyUnlike(final int storyId) {
        return storyLikeDAOImpl.storyUnlike(storyId);
    }
}
