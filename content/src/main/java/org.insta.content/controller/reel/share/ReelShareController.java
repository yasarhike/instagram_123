package org.insta.content.controller.reel.share;

import org.insta.content.dao.reel.share.ReelShareDAO;

public final class ReelShareController {

    private static ReelShareController reelShareController;
    private final ReelShareDAO reelShareDAO;

    private ReelShareController() {
        reelShareDAO = ReelShareDAO.getInstance();
    }

    public static ReelShareController getInstance() {
        return reelShareController == null ? reelShareController = new ReelShareController() : reelShareController;
    }

    public int reelShare(final int userId, final int reelId) {
        return reelShareDAO.reelShare(userId, reelId);
    }

    public boolean removeShare(final int id) {
        return reelShareDAO.removeShare(id);
    }
}
