package org.insta.main;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
public final class Activator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting : MAIN");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopping : MAIN");
    }
}
