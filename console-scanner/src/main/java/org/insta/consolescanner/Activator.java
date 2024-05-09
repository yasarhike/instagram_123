package org.insta.consolescanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * The Activator class for initializing and stopping the console scanner bundle.
 * </p>
 *
 * <p>
 * This class implements the BundleActivator interface to manage the lifecycle of the console scanner bundle.
 * It contains methods to start and stop the bundle.
 * </p>
 *
 * <p>
 * When the bundle starts, it prints a message indicating the start of the console scanner.
 * Similarly, when the bundle stops, it prints a message indicating the stop of the console scanner.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see BundleActivator
 */
public final class Activator implements BundleActivator {

    /**
     * <p>
     * Called when the console scanner bundle is started.
     * </p>
     *
     * @param bundleContext The context of the bundle.
     */
    @Override
    public void start(BundleContext bundleContext) {
        System.out.println("Starting : CONSOLE SCANNER");
    }

    /**
     * <p>
     * Called when the console scanner bundle is stopped.
     * </p>
     *
     * @param bundleContext The context of the bundle.
     */
    @Override
    public void stop(BundleContext bundleContext) {
        System.out.println("Stopping : CONSOLE SCANNER");
    }
}
