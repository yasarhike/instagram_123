package org.insta.content.service.post;

import org.insta.content.dao.post.PostServiceDAO;
import org.insta.content.dao.post.PostServiceDAOImpl;
import org.insta.content.groups.PostValidator;
import org.insta.content.model.post.Post;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import java.util.List;

/**
 * <p>
 * Implementation class for managing posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostServiceImpl implements PostService {

    private static PostServiceImpl postServiceImplementation;
    private final PostServiceDAO postServiceDAO;
    private final ObjectValidator objectValidator;


    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostServiceImpl() {
        postServiceDAO = PostServiceDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the singleton instance of PostServiceImplementation class.
     * </p>
     *
     * @return The singleton instance of PostServiceImplementation class.
     */
    public static PostServiceImpl getInstance() {
        return postServiceImplementation == null ? new PostServiceImpl()
                : postServiceImplementation;
    }

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post the post to be added
     * @return a byte array representing the result of the operation
     */
    public byte[] addPost(final Post post) {
        final byte[] violations = objectValidator.validate(post, PostValidator.class);

        return violations.length > 0 ? violations
                : objectValidator.forSuccessResponse(postServiceDAO.addPost(post), violations);
    }

    /**
     * <p>
     * Removes a post with the specified ID.
     * </p>
     *
     * @param postId the ID of the post to be removed
     * @return a byte array representing the result of the operation
     */
    public byte[] removePost(final int postId) {
        return objectValidator.manualResponse(postServiceDAO.removePost(postId));
    }

    /**
     * <p>
     * Retrieves a post with the specified ID.
     * </p>
     *
     * @param id the ID of the post to be retrieved
     * @return a byte array representing the retrieved post
     */
    public byte[] getPost(final int id) {
        final List<Post> post = postServiceDAO.displayPost(id);

        return post.isEmpty() ? objectValidator.objectResponse(post)
                : objectValidator.manualResponse(false);
    }
}