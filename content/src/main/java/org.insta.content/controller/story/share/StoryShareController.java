package org.insta.content.controller.story.share;

import org.insta.content.dao.story.share.StoryShareDAOImpl;


public final class StoryShareController {

    private static StoryShareController storyShareController;
    private final StoryShareDAOImpl storyShareDAOImp;

    private StoryShareController() {
        storyShareDAOImp = StoryShareDAOImpl.getInstance();
    }

    private StoryShareController getStoryShareDAOImp() {
        return storyShareDAOImp == null ? new StoryShareController() : storyShareController;
    }

    public int storyShare(final int storyId, final int userId) {
        return storyShareDAOImp.addShare(storyId, userId);
    }

    public boolean storyUnShare(final int storyId) {
        return storyShareDAOImp.removeShare(storyId);
    }
}
