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
     * @param story  Refer to the {@link Story} of the user.
     * @param userId The userId of the user adding the story.
     * @return True if the story is added successfully, otherwise false.
     */
    public int addStory(final Story story) {
        return storyServiceDAOImpl.addStory(story);
    }

    /**
     * <p>
     * Removes a reel with the specified ID for the specified user.
     * </P>
     *
     * @param storyId Refer to id of the story.
     * @param userId  The userId of the user removing the story.
     * @return True if the story is removed successfully, otherwise false.
     */
    public boolean removeStory(final Integer storyId) {
        return false;
    }

    /**
     * <p>
     * Displays a reel with the specified ID for the specified user.
     * </P>
     *
     * @param userId Refer to id of the story.
     * @return True if the story data is fetched successfully, otherwise false.
     */
    public List<Story> displayStory(final int userId) {
        return storyServiceDAOImpl.displayStory(userId);
    }
}
