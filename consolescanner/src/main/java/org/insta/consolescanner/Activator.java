package org.insta.consolescanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public final class Activator implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext){
        System.out.println("Starting : CONSOLE SCANNER");
    }

    @Override
    public void stop(BundleContext bundleContext){
        System.out.println("Stopping : CONSOLE SCANNER");
    }
}
