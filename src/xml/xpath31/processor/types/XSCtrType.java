/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrea Bittau                    - initial API and implementation
 *     Mukul Gandhi <mukulg@apache.org> - source code reused and adapted for use 
 *                                        with XalanJ's XPath 3.1 processor.
 *******************************************************************************/

package xml.xpath31.processor.types;

import javax.xml.transform.TransformerException;

import org.apache.xpath.objects.ResultSequence;

/**
 * A representation of the XSCtrType datatype.
 * 
 * All the XML Schema built-in types that have constructor functions
 * as defined by XPath 3.1 F&O spec, XalanJ data type classes for those 
 * XML Schema built-in types have this class as a parent or an ancestor 
 * class.
 */
public abstract class XSCtrType extends XSAnyAtomicType {

    private static final long serialVersionUID = -1177633885817069140L;

    /**
	 * This function is used for, XML Schema built-in types 
	 * constructor functions.
	 * 
	 * For e.g, xs:string($arg as xs:anyAtomicType?) as xs:string? ,
	 *          xs:boolean($arg as xs:anyAtomicType?) as xs:boolean? etc
	 *          
	 * @param arg    either an empty sequence, or an XML Schema atomic type
	 * 
	 * @return       the resulting ResultSequence
	 * 
     * @throws TransformerException 
	 */
	public abstract ResultSequence constructor(ResultSequence arg) throws TransformerException;

	/**
	 * Get the datatype's name.
	 * 
	 * @return String representation of the datatype's name
	 */
	public abstract String typeName();
	
}
