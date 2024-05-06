package org.insta.exception;

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
 * @see BundleActivator
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class Activator implements BundleActivator {

    /**
     * <p>
     * Called when the bundle is started.
     * </p>
     *
     * @param context {@link BundleContext} The context of the bundle.
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        System.out.println("Starting : EXCEPTION");
    }

    /**
     * <p>
     * Called when the bundle is stopped.
     *</p>
     *
     * @param context {@link BundleContext} The context of the bundle.
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        System.out.println("Stopping : EXCEPTION");
    }
}
