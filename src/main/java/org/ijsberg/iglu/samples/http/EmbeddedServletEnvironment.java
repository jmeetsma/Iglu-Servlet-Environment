/*
 * Copyright 2011 Jeroen Meetsma
 *
 *
 * This file is part of Iglu.
 *
 * Iglu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Iglu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Iglu.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ijsberg.iglu.samples.http;

import org.ijsberg.iglu.Cluster;
import org.ijsberg.iglu.Component;
import org.ijsberg.iglu.configuration.StandardCluster;
import org.ijsberg.iglu.configuration.StandardComponent;
import org.ijsberg.iglu.runtime.Startable;
import org.ijsberg.iglu.runtime.module.ModuleStarter;
import org.ijsberg.iglu.runtime.module.ServerEnvironment;

import java.util.Properties;

/**
 */
public class EmbeddedServletEnvironment {

	private static Startable env;

	private static Properties getWebServerProps() {
		Properties retval = new Properties();
		retval.setProperty("servlet.snoop.class", "org.ijsberg.iglu.server.http.servlet.SnoopServlet");
		retval.setProperty("servlet.snoop.url_pattern", "/snoop");
		return retval;
	}

	public static void main(String[] args) throws Exception {

		Cluster cluster = new StandardCluster();

		ServerEnvironment env = new ServerEnvironment();
		Component envModule = new StandardComponent(env);
		cluster.connect("environment", envModule);

		//System.out.println(CollectionSupport.format(env.getClass().getInterfaces(), ","));

		ModuleStarter moduleStarter = new ModuleStarter();
		Component starterModule = new StandardComponent(moduleStarter);
		cluster.connect("starter", starterModule);

		Component webServerModule = new StandardComponent(new org.ijsberg.iglu.server.http.module.SimpleJettyServletContext());
		webServerModule.setProperties(getWebServerProps());
		cluster.connect("webserver", webServerModule);

		env.start();


//		env.stop();
	}

}
