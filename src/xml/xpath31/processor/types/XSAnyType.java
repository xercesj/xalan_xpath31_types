/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Andrea Bittau                    - initial API and implementation
 *     Mukul Gandhi                     - source code reused and adapted for use 
 *                                        with XalanJ's XPath 3.1 processor.
 *******************************************************************************/

package xml.xpath31.processor.types;

import org.apache.xml.utils.FastStringBuffer;
import org.apache.xpath.objects.XObject;

/**
 * Base class for all the XML Schema types.
 * 
 * (please refer, https://www.w3.org/TR/xmlschema11-2/#built-in-datatypes
 *  that illustrates the XML Schema 1.1 built-in datatypes hierarchy)
 */
public abstract class XSAnyType extends XObject {
	
    private static final long serialVersionUID = -3385975335330221518L;

    /**
	 * Get the datatype's name. For e.g "xs:boolean", "xs:decimal".
	 * 
	 * @return datatype's name
	 */
	public abstract String stringType();

	/**
	 * Get the string representation of the value stored.
	 * 
	 * @return get the string representation of the, value 
	 *         stored adhering to this type.
	 */
	public abstract String stringValue();
	
	public void appendToFsb(FastStringBuffer fsb) {
	   fsb.append(stringValue());
	} 
	
}
