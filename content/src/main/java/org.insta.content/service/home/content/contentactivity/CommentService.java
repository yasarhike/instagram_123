package org.insta.content.service.home.content.contentactivity;


import org.insta.content.model.Comment;

/**
 * <p>
 * Interface for managing generic comment service operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface CommentService {

    /**
     * <p>
     * Adds an object to the commentService for the specified user.
     * </p>
     *
     * @param comment   Refers the object to be added.
     * @param contentId Refers the contentId of the user adding the object.
     * @return True if the object is added successfully, otherwise false.
     */
    public boolean add(final int contentId, final Comment comment);

    /**
     * <p>
     * removes an object to the service for the specified user.
     * </p>
     *
     * @param comment   Refers the object to be added.
     * @param contentId Refers the contentId of the user adding the object.
     * @return True if the object is added successfully, otherwise false.
     */
    public boolean remove(final int contentId, final Comment comment);
}
