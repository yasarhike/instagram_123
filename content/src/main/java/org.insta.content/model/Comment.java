package org.insta.content.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.insta.content.groups.CommentValidator;
import org.insta.content.model.common.Common;

public class Comment extends Common {

    @NotNull(message = "Comment id must not null", groups = CommentValidator.class)
    private int id;
    @NotNull(message = "User id must not be blank", groups = CommentValidator.class)
    private int userId;
    @NotNull(message = "Content id must not be blank", groups = CommentValidator.class)
    private int contentId;
    @NotBlank(message = "Comment id must not be blank", groups = CommentValidator.class)
    private String comment;

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }
}
