package org.insta.content;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public final class Activator implements BundleActivator {

    @Override
    public void start(final BundleContext bundleContext) {
        System.out.println("Starting : CONTENT");
    }

    @Override
    public void stop(final BundleContext bundleContext) {
        System.out.println("Stopping : CONTENT");
    }
}
