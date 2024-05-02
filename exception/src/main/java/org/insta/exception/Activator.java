package org.insta.exception;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("STARTING : Exception");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("STOPPING : Exception");
    }
}
