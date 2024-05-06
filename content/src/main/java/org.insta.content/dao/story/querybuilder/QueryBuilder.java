package org.insta.content.dao.story.querybuilder;

/**
 * <p>
 * Singleton class for building SQL queries related to story sharing operations.
 * </p>
 * <p>
 * Provides methods to generate SQL queries for sharing and unsharing stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class QueryBuilder {

    private static QueryBuilder queryBuilder;

    /**
     * <p>
     * Private constructor to prevent external instantiation.
     * </p>
     */
    private QueryBuilder() {
    }


    /**
     * <p>
     * Returns the singleton instance of QueryBuilder class.
     * </p>
     *
     * @return The singleton instance of QueryBuilder class.
     */
    public static QueryBuilder getInstance() {
        return queryBuilder == null ? new QueryBuilder() : queryBuilder;
    }

    /**
     * <p>
     * Generates SQL query for sharing a story.
     * </p>
     *
     * @return The SQL query string for sharing a story.
     */
    public String getStoryShareQuery() {
        return String.join(" ",
                "INSERT INTO story_share (story_id, shared_by)", "VALUES (?, ?)");
    }

    /**
     * <p>
     * Generates SQL query for unsharing a story.
     * </p>
     *
     * @return The SQL query string for unsharing a story.
     */
    public String getStoryUnshareQuery() {
        return String.join(" ",
                "DELETE FROM story_share where id = ?");
    }
}
