package org.insta.content.view.post.like;

import org.insta.consolescanner.SingletonScanner;
import org.insta.authentication.model.User;
import org.insta.content.controller.post.like.PostLikeController;
import org.insta.content.dao.post.like.PostLikeDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <p>
 * Represents the view for managing post likes.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostLikeView {

    private static PostLikeView postLikeView;
    private final Scanner scanner;
    private final PostLikeController postLikeController;
    private final Logger logger = LogManager.getLogger(PostLikeDAOImpl.class);

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private PostLikeView() {
        scanner = SingletonScanner.getInstance().getScanner();
        postLikeController = PostLikeController.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeView class.
     * </p>
     *
     * @return The singleton instance of PostLikeView class.
     */
    public static PostLikeView getInstance() {
        return postLikeView == null ? postLikeView = new PostLikeView() : postLikeView;
    }

    /**
     * <p>
     * Get the details for the post like.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void postLike(final User user) {
        logger.info("Enter the post id : ");
        final int postId = Integer.parseInt(scanner.nextLine());

        if (postLikeController.postLike(user.getUserId(), postId) > 0) {
            logger.debug("Like operation successful ");
        } else {
            logger.debug("Like operation failed");
        }
    }

    /**
     * <p>
     * Get the details for the post unlike.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void postUnlike(final User user) {
        logger.info("Enter the post id :");
        final int postId = Integer.parseInt(scanner.nextLine());

        if (postLikeController.postUnlike(user.getUserId(), postId)) {
            logger.debug("Unlike operation successful ");
        } else {
            logger.info("Unlike operation failed");
        }
    }
}
