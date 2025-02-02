/*******************************************************************************
 * Copyright IBM Corp. and others 2001
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which accompanies this
 * distribution and is available at https://www.eclipse.org/legal/epl-2.0/
 * or the Apache License, Version 2.0 which accompanies this distribution and
 * is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * This Source Code may also be made available under the following
 * Secondary Licenses when the conditions for such availability set
 * forth in the Eclipse Public License, v. 2.0 are satisfied: GNU
 * General Public License, version 2 with the GNU Classpath
 * Exception [1] and GNU General Public License, version 2 with the
 * OpenJDK Assembly Exception [2].
 *
 * [1] https://www.gnu.org/software/classpath/license.html
 * [2] https://openjdk.org/legal/assembly-exception.html
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0 OR GPL-2.0 WITH Classpath-exception-2.0 OR LicenseRef-GPL-2.0 WITH Assembly-exception
 *******************************************************************************/
package APITests;

import java.lang.reflect.Method;

public class FilterTesterUtils {
	
	protected static Object invokeMethod(Object obj, String name,	Object[] parameters) {

		Class[] types = new Class[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			types[i] = parameters[i].getClass();
		}
		Class c = null;
		if (obj instanceof Class)
			c = (Class) obj;
		else
			c = obj.getClass();
		while (c != null) {
			try {
				Method m = c.getDeclaredMethod(name, types);
				m.setAccessible(true);
				return m.invoke(obj, parameters);
			} catch (NoSuchMethodException e) {
				c = c.getSuperclass(); 
			} catch (Exception e) {
				break;
			}
		}
		return null;
	}

}
