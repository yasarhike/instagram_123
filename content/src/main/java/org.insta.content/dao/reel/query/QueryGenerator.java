package org.insta.content.dao.reel.query;

import org.insta.content.model.reel.Reel;

import java.util.List;

/**
 * <p>
 * Generates SQL queries based on Reel properties.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class QueryGenerator {

    private static QueryGenerator queryGenerator;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private QueryGenerator() {
    }

    /**
     * <p>
     * Returns the singleton instance of QueryGenerator class.
     * </p>
     *
     * @return The singleton instance of QueryGenerator class.
     */
    public static QueryGenerator getInstance() {
        return queryGenerator == null ? queryGenerator = new QueryGenerator() : queryGenerator;
    }

    /**
     * <p>
     * Adds the query component for the "caption" field to the query list if the "caption" property of the Reel object is not null.
     * </p>
     *
     * @param reel      The Reel object containing the "caption" property.
     * @param queryList The list to which the query component will be added.
     * @param list      The list to which the value of the "caption" property will be added.
     */
    private void getCaptionQuery(final Reel reel, final List<String> queryList, final List<Object> list) {
        if (reel.getCaption() != null) {
            queryList.add("caption = ?");
            list.add(reel.getCaption());
        }
    }

    /**
     * <p>
     * Adds the query component for the "duration" field to the query list if the "duration" property of the Reel object is not null.
     * </p>
     *
     * @param reel      The Reel object containing the "duration" property.
     * @param queryList The list to which the query component will be added.
     * @param list      The list to which the value of the "duration" property will be added.
     */
    private void getDurationQuery(final Reel reel, final List<String> queryList, final List<Object> list) {
        if (reel.getDuration() != null) {
            queryList.add("duration = ?");
            list.add(reel.getDuration());
        }
    }

    /**
     * <p>
     * Adds the query component for the "is_private" field to the query list if the "isPrivate" property of the Reel object is not null.
     * </p>
     *
     * @param reel      The Reel object containing the "isPrivate" property.
     * @param queryList The list to which the query component will be added.
     * @param list      The list to which the value of the "isPrivate" property will be added.
     */
    private void getPrivateQuery(final Reel reel, final List<String> queryList, final List<Object> list) {
        if (reel.isPrivate() == null) {
            queryList.add("is_private = ?");
            list.add(reel.isPrivate());
        }
    }

    /**
     * <p>
     * Generates SQL query components based on Reel properties.
     * </p>
     *
     * @param reel      The Reel object to generate queries for
     * @param queryList The list to store the query components
     * @param list      The list to store query parameter values
     */
    public void getQuery(final Reel reel, final List<String> queryList, final List<Object> list) {
        getCaptionQuery(reel, queryList, list);
        getDurationQuery(reel, queryList, list);
        getPrivateQuery(reel, queryList, list);
    }
}
