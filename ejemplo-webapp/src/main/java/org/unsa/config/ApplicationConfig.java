package org.unsa.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/resources")
public class ApplicationConfig  extends Application {
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> list = new HashSet<Class<?>>();
        return list;
    }
}
