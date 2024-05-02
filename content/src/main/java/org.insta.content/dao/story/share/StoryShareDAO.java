package org.insta.content.dao.story.share;

interface StoryShareDAO {

    int addShare(final int storyId, final int sharedBy);

    boolean removeShare(final int storyId);
}
