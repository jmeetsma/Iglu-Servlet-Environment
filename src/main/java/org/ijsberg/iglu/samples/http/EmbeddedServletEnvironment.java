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

import java.io.IOException;
import java.util.Properties;

import org.ijsberg.iglu.exception.ResourceException;
import org.ijsberg.iglu.server.http.module.SimpleJettyServletContext;
import org.ijsberg.iglu.util.io.FileSupport;

/**
 */
public class EmbeddedServletEnvironment {

	private static Properties getWebServerProps() {
		Properties retval = new Properties();
		try {
			retval.load(FileSupport.getInputStreamFromClassLoader("servlet_context.properties"));
		} catch (IOException ioe) {
			throw new ResourceException("can not load servlet context properties", ioe);
		}
		return retval;
	}
	
	public void start() {
		SimpleJettyServletContext servletContext = new SimpleJettyServletContext();
		servletContext.setProperties(getWebServerProps());
		servletContext.start();
	}

	public static void main(String[] args) throws Exception {

		new EmbeddedServletEnvironment().start();
	}

}
