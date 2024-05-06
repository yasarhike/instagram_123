package org.insta.content;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * The Activator class for the CONTENT bundle.
 * </p>
 *
 * <p>
 * This class implements the BundleActivator interface to start and stop the bundle.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 */
public final class Activator implements BundleActivator {

    /**
     * <p>
     * Called when the bundle is started.
     * </p>
     *
     * @param bundleContext the BundleContext for the bundle
     */
    @Override
    public void start(final BundleContext bundleContext) {
        System.out.println("Starting : CONTENT");
    }

    /**
     * <p>
     * Called when the bundle is stopped.
     * </p>
     *
     * @param bundleContext the BundleContext for the bundle
     */
    @Override
    public void stop(final BundleContext bundleContext) {
        System.out.println("Stopping : CONTENT");
    }
}
