package org.insta.content.service.home.content;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Generic implementation class for managing service operations.
 * </p>
 *
 * @param <T> Type of objects managed by the service.
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ContentServiceImpl<T> implements ContentService<T> {

    private static Integer contentCount = 0;
    private final HashMap<Integer, Map<Integer, T>> content;

    /**
     * <p>
     * Constructs a new ServiceImplementation object.
     * </p>
     */
    public ContentServiceImpl() {
        content = new HashMap<>();
    }

    /**
     * <p>
     * Retrieves the post count.
     * </p>
     *
     * @return PostCount of total post.
     */
    public static int getContentCount() {
        return contentCount;
    }

    /**
     * <p>
     * Adds an content to the service for the specified user.
     * </p>
     *
     * @param content Refers the content to be added.
     * @param userId  Refers the userId of the user adding the object.
     * @return True if the object is added successfully, otherwise false.
     */
    public boolean add(final T content, final int userId) {
        this.content.computeIfAbsent(userId, k -> new HashMap<>()).put(contentCount++, content);

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param contentId Refers the ID of the object to be removed.
     * @param userId    Refers the  userId of the user removing the object.
     * @return
     */
    public boolean remove(final int contentId, final int userId) {
        return this.content.containsKey(userId) && this.content.get(userId).remove(contentId) != null;
    }

    /**
     * <p>
     * Retrieves all contents.
     * </p>
     *
     * @return Map containing objects by usernames.
     */
    public Map<Integer, Map<Integer, T>> getContent() {
        return this.content;
    }
}
