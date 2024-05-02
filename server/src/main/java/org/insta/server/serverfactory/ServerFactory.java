package org.insta.server.serverfactory;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.osgi.framework.namespace.IdentityNamespace;

import java.util.Collection;
import java.util.Stack;

public interface ServerFactory {

    JAXRSServerFactoryBean addServiceClass();
}
