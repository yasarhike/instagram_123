package org.insta.content.view.reel.comment;

import org.insta.authentication.model.User;
import org.insta.content.controller.reel.comment.ReelCommentController;
import org.insta.content.model.Comment;
import org.insta.content.view.reelfeed.ReelsFeedView;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public final class ReelCommentView {

    private static ReelCommentView reelCommentView;
    private final ReelCommentController reelCommentController;
    private final Scanner scanner;
    private final ReelsFeedView reelsFeedView;
    private final Logger LOGGER = LogManager.getLogger(ReelCommentView.class);;

    private ReelCommentView() {
        scanner = SingletonScanner.getInstance().getScanner();
        reelCommentController = ReelCommentController.getInstance();
        reelsFeedView = ReelsFeedView.getInstance();
    }

    public static ReelCommentView getInstance() {
        return reelCommentView == null ? reelCommentView = new ReelCommentView() : reelCommentView;
    }

    public void addComment(final User user) {
        int reelId = 0;
        LOGGER.info("Enter the reel id : ");

        try {
            reelId = Integer.parseInt(scanner.nextLine());
        } catch (Exception exception) {
            addComment(user);
        }

        LOGGER.info("Enter the comment :");
        final String comment = scanner.nextLine();

        if (reelCommentController.add(new Comment()) > 0) {
            LOGGER.debug("Comment added successful");
            reelsFeedView.displayReels(user);
        } else {
            LOGGER.debug("Operation failed");
            addComment(user);
        }
    }

    public void removeComment(final User user) {
        LOGGER.info("Enter the reel id :");
        final int reelId = Integer.parseInt(scanner.nextLine());

        LOGGER.info("Enter the comment id :");
        final int comment = Integer.parseInt(scanner.nextLine());

        if (reelCommentController.deleteComment(comment)) {
            LOGGER.debug("Comment removed successfully");
            reelsFeedView.displayReels(user);
        } else {
            LOGGER.debug("Operation failed");
            removeComment(user);
        }
    }
}
