package org.insta.content.view.reel;

import org.insta.authentication.model.User;
import org.insta.content.controller.reel.ReelController;
import org.insta.content.model.reel.Reel;
import org.insta.content.view.content.Content;
import org.insta.content.view.post.userinputhandler.UserInformationHandler;
import org.insta.content.view.home.PostManager;
import org.insta.content.view.home.ProfileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * <p>
 * Represents the view for managing reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelView extends Content {

    private static ReelView reelView;
    private final UserInformationHandler userInformationHandler;
    private final ReelController reelController;
    private final ProfileManager profileManager;
    private final Logger LOGGER = LogManager.getLogger(ReelView.class);

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private ReelView() {
        reelController = ReelController.getInstance();
        userInformationHandler = UserInformationHandler.getInstance();
        profileManager = ProfileManager.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelView class.
     * </p>
     *
     * @return The singleton instance of ReelView class.
     */
    public static ReelView getInstance() {
        return reelView == null ? reelView = new ReelView() : reelView;
    }

    /**
     * <p>
     * Adds a new reel.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void add(User user) {
        final Reel reel = new Reel();

        reel.setUserId(user.getUserId());
        reel.setCaption(userInformationHandler.getCaption());
        reel.setPrivate(userInformationHandler.getIsPrivate());
        reel.setDuration(userInformationHandler.getDuration());
        reel.setHashTags(userInformationHandler.getHashTag());

        if (reelController.addReel(reel) > 0) {
            LOGGER.info("Reel added successful");
            PostManager.getInstance().uploadPost(user);
        } else {
            LOGGER.debug("Operation failed");
            PostManager.getInstance().uploadPost(user);
        }
    }

    /**
     * <p>
     * Deletes a new post.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void delete(final User user) {
        final int reelId = userInformationHandler.getPostId();

        if (reelController.removeReel(reelId)) {

            LOGGER.debug("Post removed successful");
            PostManager.getInstance().uploadPost(user);
        } else {
            LOGGER.debug("operation failed");
            PostManager.getInstance().uploadPost(user);
        }
    }

    /**
     * <p>
     * Displays all  post.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void display(final User user) {
        final List<Reel> reelList = reelController.display(user.getUserId());

        for (final Reel reel : reelList) {
            LOGGER.info(reel.toString());
        }

        if (reelList.isEmpty()) {
            LOGGER.warn("No post available");
        }

        profileManager.userProfile(user);
    }
}
