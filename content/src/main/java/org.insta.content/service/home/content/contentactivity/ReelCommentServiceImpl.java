package org.insta.content.service.home.content.contentactivity;


import org.insta.content.model.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 * <p></p>
 * Interface for managing generic comment service operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelCommentServiceImpl {

    private static ReelCommentServiceImpl commentService;
    private static int commentId;
    private final Map<Integer, List<Comment>> comments;

    /**
     * {@inheritDoc}
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelCommentServiceImpl() {
        comments = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the singleton instance of PostCommentService class.
     * </p>
     *
     * @return The singleton instance of PostCommentService class.
     */
    public static ReelCommentServiceImpl getInstance() {
        return commentService == null ? commentService = new ReelCommentServiceImpl() : commentService;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Adds an object to the commentService for the specified user.
     * </p>
     *
     * @param comment Refers the object to be added.
     * @param reelId  Refers the reelId of the user adding the object.
     * @return True if the object is added successfully, otherwise false.
     */
    public boolean add(final int reelId, final Comment comment) {
        if (comments.containsKey(reelId)) {
            comments.get(reelId).add(comment);
        } else {
            List<Comment> element = new ArrayList<>();
            element.add(comment);
            comments.put(reelId, element);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * removes an object to the service for the specified user.
     * </p>
     *
     * @param reelId Refers the object to be removed.
     * @return True if the object is removed successfully, otherwise false.
     */
    public boolean remove(final int reelId) {
        if (comments.containsKey(reelId)) {
            comments.remove(reelId);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * removes an object to the service for the specified user.
     * </p>
     *
     * @param reelId Refers the id of the reel.
     * @return List if the object is fetched successfully, otherwise null.
     */
    public List<Comment> get(final int reelId) {
        return comments.get(reelId);
    }
}
