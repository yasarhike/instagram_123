package org.insta.content.controller.story.share;

import org.insta.content.dao.story.share.StoryShareDAO;
import org.insta.content.dao.story.share.StoryShareDAOImpl;

/**
 * <p>
 * Class for managing sharing operations for stories within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides methods for sharing and unsharing stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryShareDAO
 */
public final class StoryShareController {

    private static StoryShareController storyShareController;
    private final StoryShareDAO storyShareDAOImp;

    /**
     * <p>
     * Private constructor to restrict object creation outside of the class.
     * </p>
     */
    private StoryShareController() {
        storyShareDAOImp = StoryShareDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of the StoryShareController class.
     * </p>
     *
     * @return The singleton instance of StoryShareController class.
     */
    private StoryShareController getStoryShareDAOImp() {
        return storyShareDAOImp == null ? new StoryShareController() : storyShareController;
    }

    /**
     * <p>
     * Shares a story with the specified user.
     * </p>
     *
     * @param storyId the ID of the story to share
     * @param userId  the ID of the user to share with
     * @return the ID of the shared story if successful, otherwise -1
     */
    public int storyShare(final int storyId, final int userId) {
        return storyShareDAOImp.addShare(storyId, userId);
    }

    /**
     * <p>
     * Unshares a story.
     * </p>
     *
     * @param storyId the ID of the story to unshare
     * @return true if the story is unshared successfully, otherwise false
     */
    public boolean storyUnShare(final int storyId) {
        return storyShareDAOImp.removeShare(storyId);
    }
}
