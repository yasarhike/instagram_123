package org.insta.server;

import org.insta.server.serverfactory.ServerFactory;
import org.insta.server.serverfactory.ServerFactoryImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.apache.cxf.endpoint.Server;

public final class Activator implements BundleActivator {
    private Server server;
    private final ServerFactory serverFactoryImpl;

    public Activator() {
        serverFactoryImpl = ServerFactoryImpl.getInstance();
    }
    @Override
    public void start(BundleContext context) {
        if (server == null) server = serverFactoryImpl.addServiceClass().create();
        System.out.println("Starting : SERVER");
    }

    @Override
    public void stop(BundleContext context) {
        if (server != null) server.destroy();
        System.out.println("Stopping : SERVER");
    }
}
