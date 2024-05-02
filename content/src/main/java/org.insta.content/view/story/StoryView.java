package org.insta.content.view.story;

import org.insta.authentication.model.User;
import org.insta.content.controller.story.StoryController;
import org.insta.content.model.story.Story;
import org.insta.content.view.content.Content;
import org.insta.content.view.post.userinputhandler.UserInformationHandler;
import org.insta.content.view.home.PostManager;
import org.insta.content.view.home.ProfileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * <p>
 * Represents the view for managing story.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class StoryView extends Content {

    private static StoryView storyView;
    private final UserInformationHandler userInformationHandler;
    private final StoryController storyController;
    private final ProfileManager profileManager;
    private final Logger logger = LogManager.getLogger(StoryView.class);

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private StoryView() {
        storyController = StoryController.getInstance();
        userInformationHandler = UserInformationHandler.getInstance();
        profileManager = ProfileManager.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryView class.
     * </p>
     *
     * @return The singleton instance of StoryView class.
     */
    public static StoryView getInstance() {
        return storyView == null ? storyView = new StoryView() : storyView;
    }

    /**
     * <p>
     * Adds a new story.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void add(User user) {

        final Story story = new Story();

        story.setUserId(user.getUserId());
        story.setPrivate(userInformationHandler.getIsPrivate());
        story.setMedia(userInformationHandler.getType());
        story.setText(userInformationHandler.getCaption());
        story.setMusic(userInformationHandler.getMusic());

        if (storyController.addStory(story) > 0) {
            System.out.println("Story added successful");
            PostManager.getInstance().uploadPost(user);
        } else {
            System.out.println("Operation failed");
            PostManager.getInstance().uploadPost(user);
        }
    }

    /**
     * <p>
     * Deletes a story.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void delete(User user) {
        final int storyId = userInformationHandler.getPostId();

        if (storyController.removeStory(storyId)) {
            System.out.println("story removed successful");
            PostManager.getInstance().uploadPost(user);
        } else {
            System.out.println("operation failed");
            contentHomePage(user);
        }
    }

    /**
     * <p>
     * Displays all story.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void display(final User user) {
        final List<Story> storyList = storyController.displayStory(user.getUserId());

        for (final Story story : storyList) {
            logger.info(story.toString());
        }

        if (storyList.isEmpty()) {
            logger.warn("No story available");
        }

        profileManager.userProfile(user);
    }
}
