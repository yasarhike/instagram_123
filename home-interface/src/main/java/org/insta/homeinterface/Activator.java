package org.insta.homeinterface;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public final class Activator implements BundleActivator {
    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        System.out.println("Starting : HOME INTERFACE");
    }

    @Override
    public void stop(final BundleContext bundleContext) throws Exception {
        System.out.println("Stopping : HOME INTERFACE");
    }
}
