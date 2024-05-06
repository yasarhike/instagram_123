package org.insta.content.controller.post;

import org.insta.content.dao.post.PostServiceDAOImpl;
import org.insta.content.model.post.Post;

import java.util.List;


/**
 * <p>
 * Manages posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostServiceDAOImpl
 */
public final class PostController {

    private static PostController postController;
    private final PostServiceDAOImpl postServiceDAOImpl;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostController() {
        postServiceDAOImpl = PostServiceDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostController class.
     * </p>
     *
     * @return The singleton instance of PostController class.
     */
    public static PostController getInstance() {
        return postController == null ? new PostController() : postController;
    }

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post Refer to the {@link Post} of the user.
     * @return True if the post is added successfully, otherwise false.
     */
    public int addPost(final Post post) {
        return postServiceDAOImpl.addPost(post);
    }

    /**
     * <p>
     * Removes a post with the specified ID for the specified user.
     * </P>
     *
     * @param id Refer to postId of the post.
     * @return True if the post is removed successfully, otherwise false.
     */
    public boolean removePost(final int id) {
        return postServiceDAOImpl.removePost(id);
    }

    /**
     * <p>
     * Displays the post with the specified ID.
     * </P>
     *
     * @param id Refer to user id of the user.
     * @return List if the post is fetched successfully, otherwise null.
     */
    public List<Post> display(final int id) {
        return postServiceDAOImpl.displayPost(id);
    }
}