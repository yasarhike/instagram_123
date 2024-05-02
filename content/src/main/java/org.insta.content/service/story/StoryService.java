package org.insta.content.service.story;

import org.insta.content.model.story.Story;

/**
 * <p>
 * managing story service operation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface StoryService {

    /**
     * <p>
     * Adds a story for the specified user.
     * </p>
     *
     * @param story  Refer to the {@link Story} of the user.
     * @param userId Refer the userId of the user adding the story.
     * @return True if the story is added successfully, otherwise false.
     */
    boolean addStory(final Story story, final Integer userId);

    /**
     * <p>
     * Removes a reel with the specified ID for the specified user.
     * </P>
     *
     * @param storyId Refer to id of the story.
     * @param userId  Refer the userId of the user removing the story.
     * @return True if the story is removed successfully, otherwise false.
     */
    boolean removeStory(final int storyId, final Integer userId);
}
