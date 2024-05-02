package org.insta.content.service.home.content;

/**
 * <p>
 * Interface for managing generic service operations.
 * </p>
 *
 * @param <T> The type of objects managed by the service.
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface ContentService<T> {

    /**
     * <p>
     * Adds an object to the service for the specified user.
     * </p>
     *
     * @param content Refers the object to be added.
     * @param userId  Refers the userId of the user adding the object.
     * @return True if the object is added successfully, otherwise false.
     */
    boolean add(final T content, final int userId);

    /**
     * <p>
     * Removes an object with the specified ID for the specified user.
     * </p>
     *
     * @param contentId Refers the ID of the object to be removed.
     * @param userId    Refers the  userId of the user removing the object.
     * @return True if the object is removed successfully, otherwise false.
     */
    boolean remove(final int contentId, final int userId);
}

