package org.insta.content.service.home.content.contentactivity;


import org.insta.content.model.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * Implemented class for managing post comment operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostCommentServiceImpl {

    private static PostCommentServiceImpl commentService;
    private static int commentId;

    /**
     * {@inheritDoc}
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentServiceImpl() {

    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the singleton instance of PostCommentService class.
     * </p>
     *
     * @return The singleton instance of PostCommentService class.
     */
    public static PostCommentServiceImpl getInstance() {
        return commentService == null ? commentService = new PostCommentServiceImpl() : commentService;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Adds an object to the commentService for the specified user.
     * </p>
     *
     * @param comment Refers the object to be added.
     * @param postId  Refers the contentId of the user adding the object.
     * @return True if the object is added successfully, otherwise false.
     */
    public boolean add(final int postId, final Comment comment) {
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * removes an object to the service for the specified user.
     * </p>
     *
     * @param postId Refers the object to be removed.
     * @return True if the object is added successfully, otherwise false.
     */
    public boolean remove(final int postId) {
        return false;
    }
}
