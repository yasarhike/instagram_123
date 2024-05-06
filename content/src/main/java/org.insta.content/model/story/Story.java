package org.insta.content.model.story;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.insta.content.groups.StoryValidator;
import org.insta.content.model.common.Common;
import org.insta.content.model.home.Media;

import java.sql.Timestamp;

/**
 * <p>
 * Represents a story.
 * </p>
 *
 * <p>
 * This class defines properties for a story, including the story ID, user ID,
 * content ID, and the story content itself.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Common
 */
public final class Story {

    @NotNull(message = "Is private mst not be null", groups = StoryValidator.class)
    private boolean isPrivate;
    @Positive(message = "Story id must be in positive", groups = StoryValidator.class)
    private int storyId;
    @NotNull(message = "Media field must not be null", groups = StoryValidator.class)
    private Media media;
    @NotNull(message = "Music field must not be null", groups = StoryValidator.class)
    private String music;
    @NotNull(message = "Text field must not be null", groups = StoryValidator.class)
    private String text;
    @Positive(message = "User id must be in positive", groups = StoryValidator.class)
    private int userId;
    @NotNull(message = "User name must not be null", groups = StoryValidator.class)
    private String userName;
    @PositiveOrZero(message = "Total likes must be in positive", groups = StoryValidator.class)
    private int totalLikes;
    @PositiveOrZero(message = "Total share must be in positive", groups = StoryValidator.class)
    private int totalShares;
    private Timestamp timestamp;

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(final boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(final String music) {
        this.music = music;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(final int storyId) {
        this.storyId = storyId;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(final int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(final int totalShares) {
        this.totalShares = totalShares;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
