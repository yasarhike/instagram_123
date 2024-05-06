package org.insta.databaseconnection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * Bundle activator class for handling bundle start and stop events.
 * </p>
 *
 * <p>
 * This activator class implements the {@link BundleActivator} interface, which defines
 * methods to start and stop the bundle. In this implementation, the start and stop methods
 * simply print messages indicating the bundle's lifecycle events.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see BundleActivator
 */
public final class Activator implements BundleActivator {

    /**
     * <p>
     * Called when the bundle is started.
     * </p>
     *
     * @param bundleContext The context of the bundle.
     */
    @Override
    public void start(final BundleContext bundleContext) {
        System.out.println("Starting : DATABASE CONNECTION");
    }

    /**
     * <p>
     * Called when the bundle is stopped.
     * </p>
     *
     * @param bundleContext The context of the bundle.
     */
    @Override
    public void stop(final BundleContext bundleContext) {
        System.out.println("Stopping : DATABASE CONNECTION");
    }
}
