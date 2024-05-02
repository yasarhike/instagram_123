package org.insta.content.service.post;


import org.insta.content.dao.post.PostServiceDAO;
import org.insta.content.dao.post.PostServiceDAOImpl;
import org.insta.content.groups.PostValidator;
import org.insta.content.model.post.Post;
import org.insta.content.service.home.content.ContentServiceImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import java.util.Map;

/**
 * <p>
 * Implementation class for managing posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostServiceImpl implements PostService{

    private static PostServiceImpl postServiceImplementation;
    private final PostServiceDAO postServiceDAO;
    private final ObjectValidator<Post, PostValidator> objectValidator;


    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostServiceImpl() {
        postServiceDAO = PostServiceDAOImpl.getInstance();
        objectValidator = new ObjectValidator<>();
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
        return postServiceImplementation == null ? postServiceImplementation = new PostServiceImpl()
                : postServiceImplementation;
    }

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post   Refer to the post to the user.
     * @return True if the post is added successfully, otherwise false.
     */
    public byte[] addPost(final Post post) {
        final byte[] violations = objectValidator.validate(post, PostValidator.class);
        return violations != null && violations.length > 0 ? objectValidator.forFailureResponse(violations, false)
                : objectValidator.forSuccessResponse(postServiceDAO.addPost(post), violations);
    }

    /**
     * <p>
     * Removes a post with the specified ID for the specified user.
     * </P>
     *
     * @param postId Refer to id of the post.
     * @return True if the post is removed successfully, otherwise false.
     */
    public byte[] removePost(final int postId) {
        return objectValidator.manualResponse(postServiceDAO.removePost(postId));
    }

    /**
     * <p>
     * get a post with the specified ID for the specified user.
     * </P>
     *
     * @param id Refer to id of the post.
     * @return True if the post is removed successfully, otherwise false.
     */
    public byte[] getPost(final int id) {
        final Post post = postServiceDAO.getPost(id);
        return post != null ? objectValidator.objectResponse(post)
                : objectValidator.manualResponse(false);
    }

    /**
     * <p>
     * Update a post with the specified ID for the specified user.
     * </P>
     *
     * @param post {@link Post} Refer to post of the user want to update.
     * @return True if the post is removed successfully, otherwise false.
     */
    public byte[] updatePost(final Post post) {
        return objectValidator.manualResponse(postServiceDAO.updatePost(post));
    }
}