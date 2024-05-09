package org.insta.authentication;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * Starts and stops the osgi bundle. Contains JAX-RS resources for configuring server.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class Activator implements BundleActivator {
    /**
     * <p>
     * Invoked when the bundle is started.
     * </p>
     *
     * @param bundleContext The context of the bundle.
     */
    @Override
    public void start(final BundleContext bundleContext) {
        System.out.println("Starting : USER");
    }

    /**
     * <p>
     * Invoked when the bundle is started.
     * </p>
     *
     * @param bundleContext The context of the bundle.
     */
    @Override
    public void stop(final BundleContext bundleContext) {
        System.out.println("Stopping : USER");
    }
}
