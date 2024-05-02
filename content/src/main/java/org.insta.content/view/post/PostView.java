package org.insta.content.view.post;

import org.insta.authentication.model.User;
import org.insta.content.controller.post.PostController;
import org.insta.content.model.post.Post;
import org.insta.content.view.content.Content;
import org.insta.content.view.home.ProfileManager;
import org.insta.content.view.post.userinputhandler.UserInformationHandler;
import org.insta.content.view.home.PostManager;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Represents the view for managing posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostView extends Content {

    private static PostView postView;
    private final UserInformationHandler userInformationHandler;
    private final PostController postController;
    private final Scanner scanner;
    private final PostManager postManager;
    private final ProfileManager profileManager;
    private final Logger LOGGER = LogManager.getLogger(PostView.class);

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private PostView() {
        postController = PostController.getInstance();
        userInformationHandler = UserInformationHandler.getInstance();
        scanner = SingletonScanner.getInstance().getScanner();
        postManager = PostManager.getInstance();
        profileManager = ProfileManager.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostView class.
     * </p>
     *
     * @return The singleton instance of PostView class.
     */
    public static PostView getInstance() {
        return postView == null ? postView = new PostView() : postView;
    }

    /**
     * <p>
     * Adds a new post.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void add(final User user) {
        final Post post = new Post();

        post.setUserId(user.getUserId());
        userInformationHandler.getUserDetails(post);

        if (postController.addPost(post) > 0) {

            LOGGER.debug("posted successfully");
            PostManager.getInstance().uploadPost(user);
        } else {
            LOGGER.debug("operation failed");
            PostManager.getInstance().uploadPost(user);
        }
    }

    /**
     * <p>
     * Deletes a post.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void delete(final User user) {
        final int postId = userInformationHandler.getPostId();

        if (postController.removePost(postId)) {
            LOGGER.debug("Post removed successful");
            PostManager.getInstance().uploadPost(user);
        } else {
            LOGGER.debug("Operation failed");
            PostManager.getInstance().uploadPost(user);
        }
    }

    /**
     * <p>
     * Update a post.
     * </p>
     *
     * @param post Refers to the {@link Post}.
     */
    public void update(final Post post) {
        Post updatePost = new Post();

        LOGGER.info("Want to edit the caption : ");
        post.setCaption(scanner.nextLine());

        LOGGER.info("Want to edit the privacy option (Enter yes for make private otherwise no):");
        post.setPrivate(scanner.nextLine().equalsIgnoreCase("yes"));

        if (postController.updatePost(post)) {
            System.out.println("Updated successful");
        } else {
            System.out.println("Update failed");
        }
    }

    /**
     * <p>
     * Displays all post.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void display(final User user) {
        final List<Post> postList = postController.display(user.getUserId());

        for (final Post post : postList) {
            LOGGER.info(post.toString());
        }

        if (postList.isEmpty()) {
            LOGGER.warn("No post available");
        }

        profileManager.userProfile(user);
    }
}
