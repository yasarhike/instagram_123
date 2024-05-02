package org.insta.wrapper;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public final class Activator implements BundleActivator {
    @Override
    public void start(final BundleContext context) {
        System.out.println("Starting : WRAPPER");
    }

    @Override
    public void stop(final BundleContext context) {
        System.out.println("Stopping : WRAPPER");
    }
}
