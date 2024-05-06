package org.insta.content.controller.story;

import org.insta.content.dao.story.StoryServiceDAOImpl;
import org.insta.content.model.story.Story;

import java.util.List;

/**
 * <p>
 * Implementation of the StoryController class for managing story.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class StoryController {

    private static StoryController storyController;
    private final StoryServiceDAOImpl storyServiceDAOImpl;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryController() {
        storyServiceDAOImpl = StoryServiceDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryController class.
     * </p>
     *
     * @return The singleton instance of StoryController class.
     */
    public static StoryController getInstance() {
        return storyController == null ? storyController = new StoryController() : storyController;
    }

    /**
     * <p>
     * Adds a story for the specified user.
     * </p>
     *
     * @param story the Story object to add
     * @return the ID of the added story if successful, otherwise -1
     */
    public int addStory(final Story story) {
        return storyServiceDAOImpl.addStory(story);
    }

    /**
     * <p>
     * Removes a story with the specified ID.
     * </p>
     *
     * @param storyId the ID of the story to remove
     * @return true if the story is removed successfully, otherwise false
     */
    public boolean removeStory(final int storyId) {
        return false;
    }

    /**
     * <p>
     * Displays stories for the specified user.
     * </p>
     *
     * @param userId the ID of the user whose stories to display
     * @return a list of stories belonging to the specified user
     */
    public List<Story> displayStory(final int userId) {
        return storyServiceDAOImpl.displayStory(userId);
    }
}
