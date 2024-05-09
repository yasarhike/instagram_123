package org.insta.content.model.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.insta.content.groups.PostValidator;
import org.insta.content.model.common.Common;
import org.insta.content.model.home.Media;

import java.sql.Timestamp;

/**
 * <p>
 * Contain post details of the user.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Common
 */
public final class Post extends Common {

    @NotNull(message = "User id must not be null", groups = PostValidator.class)
    @Positive(message = "User id must be positive", groups = PostValidator.class)
    private int userId;
    @NotNull(message = "Type must not be null", groups = PostValidator.class)
    private Media type;
    @NotNull(message = "User name must not be null", groups = PostValidator.class)
    private String userName;
    @NotNull(message = "Is private must not be null", groups = PostValidator.class)
    private boolean isPrivate;
    @NotNull(message = "caption must not be null", groups = PostValidator.class)
    private String caption;
    @Positive(message = "Post id must be in positive", groups = PostValidator.class)
    private int postId;
    @PositiveOrZero(message = "Like count must be in positive", groups = PostValidator.class)
    private int likeCount;
    @PositiveOrZero(message = "Comment count must be in positive", groups = PostValidator.class)
    private int commentCount;
    @PositiveOrZero(message = "Share count must be in positive", groups = PostValidator.class)
    private int shareCount;
    private Timestamp timestamp;

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(final boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public Media getType() {
        return type;
    }

    public void setType(final Media type) {
        this.type = type;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(final int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(final int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(final int likeCount) {
        this.likeCount = likeCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(final int shareCount) {
        this.shareCount = shareCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }
}
