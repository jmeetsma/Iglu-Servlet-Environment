package org.ijsberg.iglu.samples.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.ijsberg.iglu.configuration.Assembly;
import org.ijsberg.iglu.configuration.Cluster;
import org.ijsberg.iglu.configuration.Component;
import org.ijsberg.iglu.configuration.module.StandardCluster;
import org.ijsberg.iglu.configuration.module.StandardComponent;
import org.ijsberg.iglu.exception.ResourceException;
import org.ijsberg.iglu.server.http.module.SimpleJettyServletContext;
import org.ijsberg.iglu.util.io.FileSupport;

public class ServletEnvironmentAssembly implements Assembly {
	
	private Map<String, Cluster> clusters = new HashMap<String, Cluster>();
	private Cluster core;
	
	private static Properties getWebServerProps() {
		Properties retval = new Properties();
		try {
			retval.load(FileSupport.getInputStreamFromClassLoader("servlet_context.properties"));
		} catch (IOException ioe) {
			throw new ResourceException("can not load servlet context properties", ioe);
		}
		return retval;
	}

	
	public void initialize(String[] args) {
		
		core = new StandardCluster();
		
		Component jettyComponent = new StandardComponent(new SimpleJettyServletContext());
		jettyComponent.setProperties(getWebServerProps());
		
		core.connect("JettyServletContext", jettyComponent);
		clusters.put("core", core);
	}

	@Override
	public Map<String, Cluster> getClusters() {
		return clusters;
	}

	@Override
	public Cluster getCoreCluster() {
		return core;
	}


}
