package org.insta.content.model.common;

import org.insta.content.model.post.Post;
import org.insta.content.model.reel.Reel;
import org.insta.content.model.story.Story;

/**
 * <p>
 * Common class representing an entity with an ID.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Post
 * @see Reel
 * @see Story
 */
public class Common {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
