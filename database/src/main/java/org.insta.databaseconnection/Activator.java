package org.insta.databaseconnection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public final class Activator implements BundleActivator {
    @Override
    public void start(final BundleContext bundleContext) {
        System.out.println("Starting : DATABASE CONNECTION");
    }

    @Override
    public void stop(final BundleContext bundleContext) {
        System.out.println("Stopping : DATABASE CONNECTION");
    }
}
