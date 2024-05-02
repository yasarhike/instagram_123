package org.insta.content.view.post.userinputhandler;

import org.insta.content.model.home.Media;
import org.insta.consolescanner.SingletonScanner;
import org.apache.logging.log4j.LogManager;
import org.insta.content.model.post.Post;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <p>
 * Contains methods for handling user input related to user information.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class UserInformationHandler {

    private static UserInformationHandler userInformationHandler;
    private final Scanner scanner;
    private final Logger LOGGER = LogManager.getLogger(UserInformationHandler.class);;

    /**
     * <p>
     * Initialize the scanner for user input handling.
     * </P>
     */
    private UserInformationHandler() {
        scanner = SingletonScanner.getInstance().getScanner();
    }

    /**
     * <p>
     * Returns the singleton instance of UserInformationHandler class.
     * </P>
     *
     * @return The singleton instance of UserInformationHandler class.
     */
    public static UserInformationHandler getInstance() {
        return userInformationHandler == null ? userInformationHandler = new UserInformationHandler()
                : userInformationHandler;
    }

    /**
     * <p>
     * Get the caption and returns it.
     * </p>
     *
     * @return Caption of the post.
     */
    public String getCaption() {
        LOGGER.info("Enter the caption :");
        return scanner.nextLine().trim();
    }

    /**
     * <p>
     * Get the type of the post.
     * </p>
     *
     * @return Type of the post.
     */
    public Media getType() {
        LOGGER.info("Enter the type :(Press - 1 for image, Press 2 for video)");
        final Media media = Media.getMedia(Integer.parseInt(scanner.nextLine()));

        return media != null
                ? media : getType();
    }

    /**
     * <p>
     * Get the user choice to make the post private of public.
     * </p>
     *
     * @return Access of the post.
     */
    public boolean getIsPrivate() {
        LOGGER.info("Do you want the post to be private enter yes to make it private if not press any key");
        final String isPrivate = scanner.nextLine();

        return isPrivate.equalsIgnoreCase("yes");
    }

    /**
     * <p>
     * Get the post id.
     * </p>
     *
     * @return the name of the user.
     */
    public int getPostId() {
        LOGGER.info("Enter the post id");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * <p>
     * Get the duration for the content.
     * </p>
     *
     * @return the name of the user.
     */
    public String getDuration() {
        LOGGER.info("Enter the duration");
        return scanner.nextLine();
    }

    /**
     * <p>
     * Get the music for the content.
     * </p>
     *
     * @return the name of the user.
     */
    public String getMusic() {
        LOGGER.info("Enter the music :");
        return scanner.nextLine();
    }

    /**
     * <p>
     * Get the user details.
     * </p>
     */
    public void getUserDetails(final Post post) {
        post.setCaption(getCaption());
        post.setType(getType());
        post.setPrivate(getIsPrivate());
    }

    /**
     * <p>
     * Gets the  hashtag for the particular content.
     * </p>
     *
     * @return Validated hashTags.
     */

    public String getHashTag() {
        LOGGER.info("Enter the hashTags followed by # : ");
        final String hashTags = scanner.nextLine();

        return hashTags.matches("#\\w+(\\s+#\\w+)*") ? hashTags : getHashTag();
    }
}
