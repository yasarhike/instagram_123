package org.insta.content.dao.story;

import org.insta.content.model.story.Story;

/**
 * <p>
 * managing story service operation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface StoryServiceDAO {

    /**
     * <p>
     * Add a story of the user.
     * </p>
     *
     * @param story  {@link Story} Refers the story for the user.
     * @param userId Refers the userId of the user.
     * @return True if the like is added successfully, otherwise false.
     */
    int addStory(final Story story);

    boolean removeStory(final int id);

    Story getStory(final int id);
}
