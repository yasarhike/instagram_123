package org.insta.content.dao.story.like;

public interface StoryLikeDAO {

    /**
     * <p>
     * Add a like for the particular story.
     * </p>
     *
     * @param userId  Refers the userId for the user.
     * @param storyId Refers the storyId of the story.
     * @return True if the like is added successfully, otherwise false.
     */
    int storyLike(final int userId, final int storyId);

    /**
     * <p>
     * Remove a like for the particular post
     * </p>
     *
     * @param id Refers the storyId of the story.
     * @return True if the UnLike is done successfully, otherwise false.
     */
    boolean storyUnlike(final int id);
}
