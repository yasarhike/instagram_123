package org.insta.content.dao.reel.query;

import org.insta.content.model.reel.Reel;

import java.util.List;

public final class QueryGenerator {

    private static QueryGenerator queryGenerator;

    private QueryGenerator() {
    }

    public static QueryGenerator getInstance() {
        return queryGenerator == null ? queryGenerator = new QueryGenerator() : queryGenerator;
    }

    private void getCaptionQuery(final Reel reel, final List<String> queryList, final List<Object> list) {
        if (reel.getCaption() != null) {
            queryList.add("caption = ?");
            list.add(reel.getCaption());
        }
    }

    private void getDurationQuery(final Reel reel, final List<String> queryList, final List<Object> list) {
        if (reel.getDuration() != null) {
            queryList.add("duration = ?");
            list.add(reel.getDuration());
        }
    }

    private void getPrivateQuery(final Reel reel, final List<String> queryList, final List<Object> list) {
        if (reel.isPrivate() == null) {
            queryList.add("is_private = ?");
            list.add(reel.isPrivate());
        }
    }

    public void getQuery(final Reel reel, final List<String> queryList, final List<Object> list) {
        getCaptionQuery(reel, queryList, list);
        getDurationQuery(reel, queryList, list);
        getPrivateQuery(reel, queryList, list);
    }
}
