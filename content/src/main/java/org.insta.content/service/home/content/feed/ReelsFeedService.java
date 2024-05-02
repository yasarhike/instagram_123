package org.insta.content.service.home.content.feed;

import org.insta.content.model.Comment;
import org.insta.content.model.reel.Reel;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Interface for managing generic service operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface ReelsFeedService {

    /**
     * <p>
     * Gets the reels of the user.
     * </p>
     *
     * @return List if the object is fetched successfully, otherwise null.
     */
    Map<Integer, Map<Integer, Reel>> getReels();

    /**
     * <p>
     * Gets the comments of the user.
     * </p>
     *
     * @return List if the object is fetched successfully, otherwise null.
     */
    List<Comment> getComment(final int postId);
}
