package org.insta.server.serverfactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.insta.authentication.controller.UserAccountControllerRest;
import org.insta.content.controller.post.PostControllerRest;
import org.insta.content.controller.post.comment.PostCommentControllerRest;
import org.insta.content.controller.post.like.PostLikeControllerRest;
import org.insta.content.controller.post.share.PostShareControllerRest;
import org.insta.content.controller.reel.ReelControllerRest;
import org.insta.content.controller.reel.comment.ReelCommentControllerRest;
import org.insta.content.controller.reel.like.ReelLikeControllerRest;
import org.insta.content.controller.reel.share.ReelShareControllerRest;
import org.insta.content.controller.story.StoryControllerRest;
import org.insta.content.controller.story.like.StoryLikeControllerRest;
import org.insta.content.controller.story.share.StoryShareControllerRest;

import java.util.ArrayList;
import java.util.List;

public final class ServerFactoryImpl implements ServerFactory{

    private static ServerFactoryImpl serverFactoryImpl;
    private final JacksonJsonProvider jacksonJsonProvider;

    private ServerFactoryImpl() {
        jacksonJsonProvider = new JacksonJsonProvider();
    }

    public static ServerFactoryImpl getInstance() {
        return serverFactoryImpl == null ? new ServerFactoryImpl() : serverFactoryImpl;
    }

    private  JAXRSServerFactoryBean createServer(final String address, final List<Object> serviceClass) {

        final JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();

        factory.setProvider(jacksonJsonProvider);
        factory.setAddress(address);
        factory.setServiceBeans(serviceClass);

        return factory;
    }

    public JAXRSServerFactoryBean addServiceClass() {
        final List<Object> serviceClasses = new ArrayList<>();

        serviceClasses.add(ReelControllerRest.getInstance());
        serviceClasses.add(ReelLikeControllerRest.getInstance());
        serviceClasses.add(ReelCommentControllerRest.getInstance());
        serviceClasses.add(ReelShareControllerRest.getInstance());
        serviceClasses.add(PostControllerRest.getInstance());
        serviceClasses.add(PostLikeControllerRest.getInstance());
        serviceClasses.add(PostCommentControllerRest.getInstance());
        serviceClasses.add(PostShareControllerRest.getInstance());
        serviceClasses.add(StoryControllerRest.getInstance());
        serviceClasses.add(StoryLikeControllerRest.getInstance());
        serviceClasses.add(StoryShareControllerRest.getInstance());
        serviceClasses.add(UserAccountControllerRest.getInstance());

        return createServer("/instagram", serviceClasses);
    }
}
