package org.insta.content.view.reel.share;

import org.insta.consolescanner.SingletonScanner;
import org.insta.authentication.model.User;
import org.insta.content.controller.reel.share.ReelShareController;
import org.insta.content.view.reelfeed.ReelsFeedView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public final class ReelShareView {

    private static ReelShareView reelShareView;
    private final Scanner scanner;
    private final ReelShareController reelShareController;
    private final ReelsFeedView reelsFeedView;
    private final Logger LOGGER = LogManager.getLogger(ReelShareView.class);;

    private ReelShareView () {
        scanner = SingletonScanner.getInstance().getScanner();
        reelShareController = ReelShareController.getInstance();
        reelsFeedView = ReelsFeedView.getInstance();
    }

    public static ReelShareView getInstance() {
        return reelShareView == null ? reelShareView = new ReelShareView() : reelShareView;
    }

    public void shareReel(final User user) {
        int reelId = 0;
        LOGGER.info("Enter the reel id : ");

        try {
            reelId = Integer.parseInt(scanner.nextLine());
        } catch (Exception exception) {
            shareReel(user);
        }

        if (reelShareController.reelShare(user.getUserId(), reelId) > 0) {
            LOGGER.debug("Shared successful");
            reelsFeedView.displayReels(user);
        } else {
            LOGGER.debug("Operation failed");
            shareReel(user);
        }
    }

    public void unShareReel(final User user) {
        LOGGER.info("Enter the reel id :");
        final int reelId = Integer.parseInt(scanner.nextLine());

        LOGGER.info("Enter the share id :");
        final int shareId = Integer.parseInt(scanner.nextLine());

        if (reelShareController.removeShare(shareId)) {
            LOGGER.debug("Unshared success full");
            reelsFeedView.displayReels(user);
        } else {
            LOGGER.debug("Operation failed");
            unShareReel(user);
        }
    }
}
