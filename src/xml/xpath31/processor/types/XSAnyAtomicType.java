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
 * Base class for all the XML Schema atomic data types.
 */
public abstract class XSAnyAtomicType extends XSAnySimpleType {
    
    private static final long serialVersionUID = 4800376096762047151L;

    /*
     * This function supports, creating XML Schema built-in types, XPath 3.1
     * XDM objects with data types xs:boolean, xs:decimal etc.
     * 
     */
    public abstract ResultSequence constructor(ResultSequence arg) throws TransformerException;
    
    /**
     * Get the datatype's name
     * 
     * @return  String representation of the datatype's name
     */
    public abstract String typeName();
    
}
