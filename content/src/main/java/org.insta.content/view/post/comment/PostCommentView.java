package org.insta.content.view.post.comment;

import org.insta.consolescanner.SingletonScanner;
import org.insta.authentication.model.User;
import org.insta.content.controller.post.comment.PostCommentController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.model.Comment;

import java.util.Scanner;

/**
 * <p>
 * Represents the view for managing post comments.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostCommentView {

    private static PostCommentView postCommentView;
    private final Scanner scanner;
    private final PostCommentController postCommentController;
    private final Logger LOGGER = LogManager.getLogger(PostCommentView.class);

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentView() {
        scanner = SingletonScanner.getInstance().getScanner();
        postCommentController = PostCommentController.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentView class.
     * </p>
     *
     * @return The singleton instance of PostCommentView class.
     */
    public static PostCommentView getInstance() {
        return postCommentView == null ? postCommentView = new PostCommentView() : postCommentView;
    }

    /**
     * <p>
     * Get the details for the comment.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void addComment(final User user) {
        LOGGER.info("Enter the post id : ");
        final int postId = Integer.parseInt(scanner.nextLine());

        LOGGER.info("Enter the comment :");
        final String comment = scanner.nextLine();

        if (postCommentController.addComment(new Comment()) != 0) {
            LOGGER.debug("Comment operation successful ");

        } else {
            LOGGER.debug("Like operation failed");
        }
    }

    /**
     * <p>
     * Get the details for the particular post for comment.
     * </p>
     *
     * @param user Refers to the {@link User}.
     */
    public void removeComment(final User user) {
        LOGGER.info("Enter the post id :");
        final int postId = Integer.parseInt(scanner.nextLine());

        LOGGER.info("Enter the comment id :");
        final int commentId = Integer.parseInt(scanner.nextLine());

        if (postCommentController.removeComment(commentId)) {
            LOGGER.debug("Remove comment operation successful ");
        } else {
            LOGGER.info("Remove comment operation failed");
        }
    }
}
