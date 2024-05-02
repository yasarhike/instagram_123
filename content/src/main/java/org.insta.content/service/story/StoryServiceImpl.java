package org.insta.content.service.story;

import org.insta.content.model.story.Story;
import org.insta.content.service.home.content.ContentServiceImpl;

import java.util.Map;

/**
 * <p>
 * Implementation class for managing story.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class StoryServiceImpl {

    private static StoryServiceImpl storyServiceImpl;
    private final ContentServiceImpl<Story> storyService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryServiceImpl() {
        storyService = new ContentServiceImpl<>();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryServiceImplementation class.
     * </p>
     *
     * @return The singleton instance of StoryServiceImplementation class.
     */
    public static StoryServiceImpl getInstance() {
        return storyServiceImpl == null ? storyServiceImpl = new StoryServiceImpl()
                : storyServiceImpl;
    }

    /**
     * <p>
     * Adds a story for the specified user.
     * </p>
     *
     * @param story  Refer to the {@link Story} of the user.
     * @param userId Refer the userId of the user adding the story.
     * @return True if the story is added successfully, otherwise false.
     */
    public boolean addStory(final Story story, final Integer userId) {
        return storyService.add(story, userId);
    }

    /**
     * <p>
     * Removes a reel with the specified ID for the specified user.
     * </P>
     *
     * @param storyId Refer to id of the story.
     * @param userId  Refer the userId of the user removing the story.
     * @return True if the story is removed successfully, otherwise false.
     */
    public boolean removeStory(final int storyId, final Integer userId) {
        return storyService.remove(storyId, userId);
    }

    /**
     * <p>
     * Retrieves all story.
     * </p>
     *
     * @return Map contains the user story.
     */
    public Map<Integer, Map<Integer, Story>> getStory() {
        return storyService.getContent();
    }
}
